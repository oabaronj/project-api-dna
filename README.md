# project-api-dna
Api rest para procesar y descubrir cadenas de DNA mutante y humano


1 - URL para comprobar si una secuencia es DNA mutante o humano (Metodo POST):
https://project-api-dna.uc.r.appspot.com/mutant

Instrucciones:
Se debe enviar una secuencia de DNA por medio del metodo POST por medio de un archivo JSON utilizando una herramienta como postman, con la siguiente estructura de ejemplo:

{
    "dna":["ATGCCA","CAGTGC","TTATGT","AGAAGG","CCCTTA","TCACTG"]
}




2 - URL para obtener estadisticas del DNA mutante y humnano que se han procesado(Metodo GET):
https://project-api-dna.uc.r.appspot.com/stats

Instrucciones:
Se debe hacer solicitud por medio del metodo GET utilizando una herramienta como postman, no es necesario enviar parametros.
