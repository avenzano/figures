# Comentarios de implementación

## Factory 

La implementación de la factory es muy básica, pero en este caso no hacía falta mas que ocultar la clase de figura concreta creada retornando una interfaz.
Se configuró la factory en Spring de modo que el servicio pueda usarla directamente al guardar una nueva figura.

## FigureData

Se creó una clase para el pasaje de datos desde y hacia interface REST. Aunque parece lo mismo, esto sirve para separar la representación de las figuras de el objeto JSon que se retorna desde los metodos del controller. Asi, si la implementación de las figuras cambia, no se verá desde el código (porque usamos una interfaz) ni desde los clientes REST (porque las figuras se convierten a un DTO antes de retornarse).

## Errores

Todos los errores se manejaron con un *ControllerAdvice* de Spring, que captura todas las excepciones y nos deja retornar el error HTTP correspondiente (400 en este caso, para cualquier input inválido).

## Persistencia

Se manejó con un Map directamente dentro del servicio, ya que no se pedía persistencia. Esto provee lo básico: que se pueda guardar algo y después recuperarlo. Las consultas se hicieron con un filter sobre el stream de los values del map.

## REST

Se resolvió creando un único resource 'figures' con una acción POST para crear nuevas figuras y GET para recuperar los datos de una figura en particular (para eso se generó automáticamente un id al momento de guardar la figura) y usando parametros para realizar consultas.
*Las consultas se pueden realizar especificando 'rangos' sobre cada una de las propiedades numéricas y el tipo de figura*. Por ejemplo podemos buscar las figuras que tengan una area entre 40 y 60 de la siguiente manera:

GET http://localhost:8080/figures?minArea=40&maxArea=60

la sintaxis es igual para el resto de las propiedades de una figura: diameter, base, height. Si sólo se especifica un mínimo o máximo, entonces se buscarán las figuras que sean mayores o menores que el valor proporcionado. *El parámetro 'type' se usa para identificar directamente el tipo de figura (circle, triangle, rectangle)*

*El case de los parametros no es importante; se reconocerán en cualquier caso*

*Los parámetros inválidos son ignorados (para simplificar), mientras que los parametros que sólo aplican a una figura filtran efectivamente a las demás (por ejemplo si buscamos por diámetro sólo recuperaremos círculos, ya que ninguna otra figura tiene diámetro)*

**Detalle de los endpoints**

**GET** /figures/{id}

Retorna UNA figura correspondiente al id proporcionado (el id se retorna en el body al crear la figura)

**GET** /figures/?_parámetros_

(Siendo _parámetros_ una url compuesta como se explico mas arriba) Retorna una lista de figuras que cumplan con las condiciones especificadas en los parámetros _(nota: no especificar ningún parámetro retornará todas las figuras)_.

**POST** /figures

Especificando Content-Type a 'application/json' y un body con los siguientes requerimientos:

```json
{
  "type" : "CIRCLE",
  "diameter" : <number>
}
```
o bien:
```json
{
  "type" : ["RECTANGLE"|"TRIANGLE"],
  "base" : <number>,
  "height" : <number>
}
```

en ambos casos se retornará un body que incluirá el id de la figura para poder recupearla luego (código http 200 por supuesto)

## Diagrama UML

El diagrama UML del modelo de datos se encuentra en el **docs/Diagrama_UML.png**
