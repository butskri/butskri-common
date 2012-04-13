package be.butskri.commons.test;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

public class CompositeClassRepository extends ClassRepository {
    
    private Collection<ClassRepository> repositories;
    
    public static CompositeClassRepository create(Class<?>... rootDirClasses) {
        Collection<ClassRepository> repositories = new HashSet<ClassRepository>();
        for (Class<?> rootDirClass: rootDirClasses) {
            repositories.add(getClassRepositoryForClass(rootDirClass));
        }
        
        return new CompositeClassRepository(repositories);
    }
    
    public CompositeClassRepository(Collection<ClassRepository> repositories) {
        this.repositories = repositories;
    }
    
    @Override
    protected Collection<Class<?>> findAllClasses(Predicate predicate) {
        Collection<Class<?>> classes = new HashSet<Class<?>>();
        
        for (ClassRepository repository: repositories) {
            classes.addAll(repository.findAllClasses(predicate));
        }
        
        return classes;
    }

    private static ClassRepository getClassRepositoryForClass(Class<?> rootDirClass) {
        ClassRepository result = null;
        
        File rootFile = getRootClasspathFileForClass(rootDirClass);
        if (isJarFile(rootFile)) {
            String jarFileLocation = getJarFileLocation(rootFile);
            result = new JarFileClassRepository(new File(jarFileLocation));
        } else {
            result = new DirectoryClassRepository(rootFile);
        }
        
        return result;
    }
    
    private static File getRootClasspathFileForClass(Class<?> rootDirClass) {
        String relativePathToClassFile = StringUtils.replace(rootDirClass.getName(), ".", "/") + ".class";
        File classFile = new File(rootDirClass.getResource("/" + relativePathToClassFile).getFile());
        String absolutePathToClassFile = classFile.toString();
        
        File rootDir = new File (absolutePathToClassFile.substring(0, absolutePathToClassFile.length() - relativePathToClassFile.length()));
        
        return rootDir;
    }
    
    private static boolean isJarFile(File file) {
        return file.getName().endsWith("jar!");
    }

    private static String getJarFileLocation(File file) {
        String jarFileLocation = file.toString();
        jarFileLocation = StringUtils.replace(jarFileLocation, "\\", "/");
        jarFileLocation = StringUtils.replace(jarFileLocation, "%20", " ");
        jarFileLocation = StringUtils.replace(jarFileLocation, "file:/", "");
        jarFileLocation = StringUtils.replace(jarFileLocation, "jar!", "jar");
        
        return jarFileLocation;
    }
    
}
