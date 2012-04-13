package be.butskri.commons.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.collections.Predicate;

public class JarFileClassRepository extends ClassRepository {
    
    private JarFile jarFile;
    
    public JarFileClassRepository(File file) {
        try {
            jarFile = new JarFile(file);
        } catch (IOException ex) {
            throw new RuntimeException("could not create jarFile for file " + file, ex);
        }
    }
    
    @Override
    protected Collection<Class<?>> findAllClasses(Predicate predicate) {
        Collection<Class<?>> classes = new HashSet<Class<?>>();
        
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            
            Class<?> clazz = getClazz(jarEntry);
            if (clazz != null && predicate.evaluate(clazz)) {
                classes.add(clazz);
            }
        }
        return classes;
    }
    
    private Class<?> getClazz(JarEntry jarEntry) {
        Class<?> clazz = null;
        
        if (!jarEntry.isDirectory()) {
            String pathToClassFile = jarEntry.getName();
            clazz = getClassByRelativePath(pathToClassFile);
        }
        
        return clazz;
    }
    
}
