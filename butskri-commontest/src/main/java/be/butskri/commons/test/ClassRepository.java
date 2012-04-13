package be.butskri.commons.test;

import java.io.File;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.AndPredicate;

public abstract class ClassRepository {
    
    public Collection<Class<?>> findAllExtendingInterfaces(Class<?> implementedInterface) {
        Predicate predicate = new AndPredicate(new IsInterfacePredicate(), new IsAssignableFromPredicate(implementedInterface));
        return findAllClasses(predicate);
    }
    
    public Collection<Class<?>> findAllAssignableClasses(Class<?> clazz) {
        return findAllClasses(new IsAssignableFromPredicate(clazz));
    }
    
    public Collection<Class<?>> findAllClassesMatchingPattern(String pattern) {
        return findAllClasses(new MatchesPatternPredicate(pattern));
    }
    
    protected abstract Collection<Class<?>> findAllClasses(Predicate predicate);
    
    protected static Class<?> getClassByRelativePath(String relativePathToClassFile) {
        Class<?> clazz = null;
        
        if (relativePathToClassFile.endsWith(".class")) {
            try {
                String classname = relativePathToClassFile.substring(0, relativePathToClassFile.lastIndexOf(".")).replace(File.separatorChar, '.');
                classname = classname.replace("/", ".");
                clazz = Class.forName(classname);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return clazz;
    }

    private static class IsAssignableFromPredicate implements Predicate{

        private Class<?> targetClass;
        
        public IsAssignableFromPredicate(Class<?> targetClass) {
            this.targetClass = targetClass;
        }

        @Override
		public boolean evaluate(Object obj) {
            if ( obj instanceof Class<?> && targetClass.isAssignableFrom((Class<?>) obj)) {
                return true;
            }
            return false;
        }
        
    }
    
    private static class IsInterfacePredicate implements Predicate{

        public IsInterfacePredicate() {
        }

        @Override
		public boolean evaluate(Object obj) {
            return ( obj instanceof Class<?> && ((Class<?>) obj).isInterface());
        }
        
    }
    
    private static class MatchesPatternPredicate implements Predicate {

        
        private Pattern pattern;

        public MatchesPatternPredicate(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        @Override
		public boolean evaluate(Object arg0) {
            if (!(arg0 instanceof Class<?>))
                return false;
            Class<?> clazz = (Class<?>) arg0;
            Matcher m = pattern.matcher(clazz.getName());
            return m.matches();
        }
        
    }
}
