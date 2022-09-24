package tup.inyedep;

import org.springframework.stereotype.Service;

/**
 * Esta segunda clase también implementa la interfaz DemoService.
 * Si no hacemos nada más que agregar la clase, el compilador dará
 * un error, indicando que hay dos beans candidatos, y no consigue
 * decidirse por ninguno.
 * Hay un par de mecanismos de desambiguación, que están explicados en
 * los comentarios de la clase DemoController.
 */
@Service
public class DemoServiceAlphaImpl implements DemoService {
}