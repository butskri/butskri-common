package be.butskri.commons.test;

import static be.butskri.commons.test.BaseAssert.*;

import java.util.Collection;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;

import org.apache.commons.collections.OrderedBidiMap;
import org.apache.commons.collections.Predicate;

public class CompositeClassRepositoryTest extends TestCase {
    
    @SuppressWarnings("unchecked")
    public void testFindAllExtendingInterfacesInJarFile() {
        CompositeClassRepository repository = CompositeClassRepository.create(Predicate.class);
        
        Collection<Class<?>> interfaces = repository.findAllExtendingInterfaces(Map.class);
        assertTrue(interfaces.size() > 0);
        assertContains(interfaces, OrderedBidiMap.class);
    }
    
    @SuppressWarnings("unchecked")
    public void testFindAllExtendingInterfacesInDir() {
        CompositeClassRepository repository = CompositeClassRepository.create(CompositeClassRepositoryTest.class);
        
        Collection<Class<?>> interfaces = repository.findAllExtendingInterfaces(Test.class);
        assertTrue(interfaces.size() > 0);
        assertContains(interfaces, MyTest.class);
    }
    
    public static interface MyTest extends Test {
        
    }
}
