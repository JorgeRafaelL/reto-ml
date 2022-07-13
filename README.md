# reto-ml
Reto de mercado libre

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Programación dinámica](https://www.wextensible.com/temas/programacion-dinamica)
* [EL problema de la mochila](https://www.wextensible.com/temas/programacion-dinamica/mochila.html)
* [Optimización Combinatoria](https://es.wikipedia.org/wiki/Optimizaci%C3%B3n_combinatoria)
* [Combinatorics3 Lib --Subset--](https://github.com/dpaukov/combinatoricslib3)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web.reactive)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

## Guía para levantar el proyecto
* Descargar o clonar el proyecto: https://github.com/JorgeRafaelL/reto-ml
  - Clonar: ```git clone https://github.com/JorgeRafaelL/reto-ml.git```
  - Abrir el proyecto en tu entorno de desarrollo favorito
  - Levantar el proyecto Spring boot: ```mvn spring-boot:run```

## Ejecutar peticiones en el API desplegada en AWS
* POST → /coupon/
  - ```curl -X POST -H "Content-Type: application/json" -d '{"item_ids": ["MLA1115590875","MLA805109897","MLA1117089595","MLA930670110","MLA934736858","MLA805100490"], "amount": 98000}' http://ec2-18-188-150-6.us-east-2.compute.amazonaws.com:10550/coupon```
* GET → /coupon/stats
  - ```curl -X GET http://ec2-18-188-150-6.us-east-2.compute.amazonaws.com:10550/coupon/stats```

