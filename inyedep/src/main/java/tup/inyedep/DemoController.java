package tup.inyedep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * La inyección de dependencias se conoce también como inversión del control.
 * En los comentarios que hay más adelante en esta misma clase se explica esta oposición.
 * Se muestra cómo sería el proceso directo, y luego se usa la inversión de control.
 */

/**
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html
 * La anotación @Controller es una especialización de @Component.
 * Permite que las clases de implementación sean detectadas en el scanning del
 * classpath.
 * Típicamente se usa en combinación con métodos handlers, basados en
 * anotaciones @RequestMapping. Precisamente eso es lo que hace este ejemplo.
 */
@Controller
public class DemoController {
    /**
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html
     * La anotación @Autowired marca el field demoService como autowired. Esto
     * significa que el mecanismo de inyección de dependencias, al hacer el scanning
     * del classpath se ocupará de todo lo necesario.
     * Lo único que hacemos es declarar el campo y anotarlo.
     * No importamos la clase. No llamamos al constructor.
     * Lo usamos, pero no lo inicializamos nosotros
     * explícitamente. Todo lo necesario lo hizo Spring.
     * Pero para que esto funcione, es necesario que la clase
     * DemoServiceImpl esté anotada con @Service.
     */
    @Autowired
    /**
     * Una opción de desambiguación es por medio de la anotación @Qualifier.
     * Esta anotación toma un parámetro, que es el nombre del bean,
     * que a su vez es el nombre de la clase, solo que con inicial
     * minúscula.
     */
    @Qualifier("demoServiceImpl")
    // @Qualifier("demoServiceAlphaImpl")
    /**
     * La línea siguiente es una inyección de campo. Notar que lo único que hacemos
     * es declarar el campo que queremos inyectar, y anotarlo con @Autowired.
     */
    private DemoService demoService;
    /**
     * Otra opción de desambiguación es llamar al campo que queremos inyectar
     * exactamente como la clase, solo que con inicial minúscula, como se muestra en
     * las dos líneas siguientes.
     */
    // private DemoService demoServiceImpl;
    // private DemoService demoServiceAlphaImpl;

    /**
     * La línea siguiente pertenece a una inyección por constructor.
     * Declaramos el campo, pero no lo anotamos con @Autowired.
     */
    private DemoServiceA demoServiceAImpl;

    /**
     * Este es el constructor que vamos a usar para la inyección por constructor.
     * No usamos @Autowired. Este constructor toma como argumento el bean que
     * queremos inyectar, y asigna el campo local this.demoServiceAImpl.
     * Como tenemos dos clases (o beans) que implementan la interfaz DemoServiceA,
     * necesitamos un mecanismo de desambiguación.
     * Optamos por usar el nombre del bean, que es el nombre de la clase de
     * implementación, pero con inicial minúscula. Podríamos usar @Qualifier,
     * pero elegimos este modo.
     */
    public DemoController(DemoServiceA demoServiceAImpl) {
        this.demoServiceAImpl = demoServiceAImpl;
    }

    /**
     * Este es el método que usamos para demostrar que la inyección por constructor
     * está funcionando.
     */
    @GetMapping("/hola")
    @ResponseBody
    public String hola() {
        return "Hola, demoServiceA: " + demoServiceAImpl;
    }

    /**
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/GetMapping.html
     * La anotación @GetMapping es una anotación compuesta, que actúa como un
     * cortocircuito de @RequestMapping(method = RequestMethod.GET)
     * Mapea requests HTTP GET en un método handler específico
     */
    @GetMapping("/")
    /**
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseBody.html
     * Esta anotación indica que el valor de retorno del método estará incorporado
     * al response body. Habrá response porque nosotros estamos mandando un request.
     * En este caso, es un request HTTP, método GET. El request lo mandamos
     * cuando en el browser vamos a la URL http://localhost:8080
     */
    @ResponseBody
    public String hello() {
        /**
         * Podemos elimiminar toda referencia a DemoService.
         * Si hacemos eso, descomentamos la instrucción siguiente y veremos en la
         * pantalla el mensaje correspondiente.
         */
        // return "Hello, demoService no autowired.";

        /**
         * Si declaramos en campo demoService pero no ponemos @Autowired, el compilador
         * no nos ofrecerá importar la clase DemoService, ni dará error. Pero al
         * ejecutar la aplicación, el campo demoService será null.
         * Esto lo veremos en la ventana del browser.
         * Eso lo podríamos arreglar llamando explícitamente al constructor.
         * Justamente, la anotación hace todo esto por nosotros.
         */
        // demoService = new DemoServiceImpl();
        return "Hello, demoService: " + demoService;
        /**
         * Las dos líneas siguientes corresponden a la opción de desambiguación
         * en la que le damos al campo inyectado el nombre del bean.
         */
        // return "Hello, demoService: " + demoServiceImpl;
        // return "Hello, demoService: " + demoServiceAlphaImpl;
    }
}