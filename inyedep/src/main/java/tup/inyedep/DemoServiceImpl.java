package tup.inyedep;

import org.springframework.stereotype.Service;

/**
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html
 * Esta anotación es una especialización de @Component.
 * Permite que las clases que implementan intefaces sean detectadas en el
 * scanning del classpath.
 * Si no pongo esta anotación, no se puede crear el bean demoController, que
 * tiene una referencia a esta clase, y arroja una excepción:
 * org.springframework.beans.factory.UnsatisfiedDependencyException
 * Esta anotación, entonces, trabaja coordinadamente con la
 * anotación @Aautowired que usamos en la clasle DemoController.
 */
@Service
/**
 * Esta clase implementa la interfaz DemoService.
 * Notar que ni siquiera escribimos un constructor. Las
 * anotaciones hacen todo el trabajo por nosotros.
 */
public class DemoServiceImpl implements DemoService {
}
