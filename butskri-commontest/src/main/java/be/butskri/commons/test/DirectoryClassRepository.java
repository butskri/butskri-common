package be.butskri.commons.test;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.collections.Predicate;

public class DirectoryClassRepository extends ClassRepository {
    
    private File rootDir;
    
    public DirectoryClassRepository(File dir) {
        this.rootDir = dir;
    }
    
    @Override
    protected Collection<Class<?>> findAllClasses(Predicate predicate) {
        return findMatchingClassesInDir(rootDir, predicate);
    }
    
    private Collection<Class<?>> findMatchingClassesInDir(File dir, Predicate predicate) {
        Collection<Class<?>> classes = new HashSet<Class<?>>();
        for (File file : dir.listFiles()) {
            if ( file.isDirectory() ) {
                classes.addAll(findMatchingClassesInDir(file, predicate));
            } else {
                Class<?> clazz = getClazz(file);
                
                if (clazz != null && predicate.evaluate(clazz)) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }

    private Class<?> getClazz(File file) {
        String relativePathToFile = file.getPath().substring(rootDir.getPath().length() + 1);
        Class<?> clazz = getClassByRelativePath(relativePathToFile);
        return clazz;
    }



    
}
