# cp3_enterprise
Entrega Global Solution 3 - Enterprise App Development.

Alunos:

RM 96553 - Felipe Sendai

RM 96542 - Gustavo Ballogh

RM 97041 - Johann Marzolla

RM 97306 - Lau Costa

RM 97373 - Raquel Calmon

### [GitHub: Vai dar ruim? ](https://github.com/r4cs/gs3)

### Maneira como se propõe a solucionar:

O projeto VaiDaRuim propõe abordar a conscientização sobre os riscos associados às misturas de substâncias por meio de uma plataforma que fornece informações detalhadas sobre as interações entre diferentes medicamentos e drogas recreativas. A solução inclui:

* Desenvolvimento de API para comunicação entre o frontend e o backend.

* Cadastro de Substâncias: Os usuários podem cadastrar diferentes substâncias, sejam medicamentos prescritos ou drogas recreativas, incluindo informações como nome, classificação e possíveis efeitos colaterais.

* Padronização de interações entre substâncias: A plataforma analisa e fornece informações sobre as interações entre as substâncias cadastradas. Isso ajuda os usuários a entenderem os potenciais riscos associados a determinadas combinações.

* Classificação de Riscos: Cada interação é classificada com base em níveis de risco, ajudando os usuários a identificarem a gravidade das possíveis consequências.

* Desenvolvimento Backend: Implementação de lógica de negócios, manipulação de dados, e lógica de análise de interações.

* Banco de Dados: Armazenamento seguro e eficiente das informações sobre substâncias e interações.


### Tecnologias Utilizadas:
* Linguagem: Java 17
* Framework Web: Spring Boot
* API REST: Spring REST
* Persistência de Dados: Hibernate / JPA
* Sistema de Gerenciamento de Banco de Dados: Oracle
* Gestão de Dependências: Maven
* Controle de Versão: GitHub
* Documentação: SpringDog OpenAPI

### Modo execução develpment:
* [application.properties.xml](src%2Fmain%2Fresources%2Fapplication.properties):
  * Determinar em spring.jpa.hibernate.ddl-auto desejado
  * Inlcuir usuario e senha
* Rodar [FarmacoController.java](src%2Fmain%2Fjava%2Fbr%2Fcom%2Fvaidaruim%2Fgs3%2FapiController%2FFarmacoController.java)
* Acesse http://localhost:8080/swagger-ui/index.html
* Ex de jsons para insert:
  * {
    "nomeDaSubstancia":"Maconha",
    "cruzamentos":{
    "MACONHA":"AZUL",
    "LSD":"VERDE",
    "COGUMELO":"VERDE",
    "CAFEÍNA":"VERDE",
    "MDMA":"VERDE",
    "KETAMINA":"VERDE",
    "ÁLCOOL":"VERDE",
    "GHB":"VERDE",
    "COCAÍNA":"VERDE",
    "POPPERS":"VERDE",
    "VIAGRA":"VERDE"
    }
    }
  
  * {
"nomeDaSubstancia":"LSD",
"cruzamentos":{
"LSD":"AZUL",
"MACONHA":"VERDE",
"COGUMELO":"VERDE",
"CAFEÍNA":"VERDE",
"MDMA":"VERDE",
"KETAMINA":"VERDE",
"ÁLCOOL":"VERDE",
"GHB":"VERDE",
"COCAÍNA":"AMARELO",
"POPPERS":"AMARELO",
"VIAGRA":"AMARELO"
}
} 
  * {
"nomeDaSubstancia":"COGUMELO",
"cruzamentos":{
"COGUMELO":"VERDE",
"MACONHA":"VERDE",
"CAFEINA":"AMARELO",
"MDMA":"VERDE",
"KETAMINA":"VERDE",
"ÁLCOOL":"VERDE",
"GHB":"VERDE",
"COCAÍNA":"AMARELO",
"POPPERS":"VERMELHO",
"VIAGRA":"VERMELHO"
}
} 
  * {
"nomeDaSubstancia":"CAFEÍNA",
"cruzamentos":{
"CAFEÍNA":"AZUL",
"MACONHA":"VERDE",
"LSD":"VERDE",
"COGUMELO":"AMARELO",
"MDMA":"AMARELO",
"KETAMINA":"AMARELO",
"ÁLCOOL":"AMARELO",
"COCAÍNA":"AMARELO",
"POPPERS":"AMARELO",
"VIAGRA":"AMARELO"
}
} 
  * {
"nomeDaSubstancia":"MDMA",
"cruzamentos":{
"MACONHA":"VERDE",
"LSD":"VERDE",
"COGUMELO":"VERDE",
"CAFEÍNA":"AMARELO",
"MDMA":"AZUL",
"KETAMINA":"AMARELO",
"ÁLCOOL":"VERMELHO",
"GHB":"AMARELO",
"COCAÍNA":"VERMELHO",
"POPPERS":"VERMELHO",
"VIAGRA":"AMARELO"
}
} 
  * {
"nomeDaSubstancia":"KETAMINA",
"cruzamentos":{
"MACONHA":"VERDE",
"LSD":"VERDE",
"COGUMELO":"VERDE",
"CAFEÍNA":"AMARELO",
"MDMA":"AMARELO",
"KETAMINA":"AZUL",
"ÁLCOOL":"PRETO",
"GHB":"PRETO",
"COCAÍNA":"VERMELHO",
"POPPERS":"VERDE",
"VIAGRA":"VERDE"
}
} 
  * {
"nomeDaSubstancia":"ÁLCOOL",
"cruzamentos":{
"MACONHA":"VERDE",
"LSD":"VERDE",
"COGUMELO":"VERDE",
"CAFEÍNA":"AMARELO",
"MDMA":"VERMELHO",
"KETAMINA":"PRETO",
"ÁLCOOL":"AZUL",
"GHB":"PRETO",
"COCAÍNA":"VERMELHO",
"POPPERS":"VERMELHO",
"VIAGRA":"VERMELHO"
}
} 
  * {
"nomeDaSubstancia":"GHB",
"cruzamentos":{
"MACONHA":"VERDE",
"LSD":"VERDE",
"COGUMELO":"VERDE",
"CAFEÍNA":"AMARELO",
"MDMA":"AMARELO",
"KETAMINA":"PRETO",
"ÁLCOOL":"PRETO",
"GHB":"AZUL",
"COCAÍNA":"VERMELHO",
"POPPERS":"VERMELHO",
"VIAGRA":"VERMELHO"
}
} 
  * {
"nomeDaSubstancia":"COCAÍNA",
"cruzamentos":{
"MACONHA":"VERDE",
"LSD":"AMARELO",
"COGUMELO":"AMARELO",
"CAFEÍNA":"AMARELO",
"MDMA":"VERMELHO",
"KETAMINA":"VERMELHO",
"ÁLCOOL":"VERMELHO",
"GHB":"VERMELHO",
"COCAÍNA":"AZUL",
"POPPERS":"PRETO",
"VIAGRA":"PRETO"
}
} 
  * {
"nomeDaSubstancia":"POPPERS",
"cruzamentos":{
"MACONHA":"VERDE",
"LSD":"AMARELO",
"COGUMELO":"VERMELHO",
"CAFEÍNA":"AMARELO",
"MDMA":"VERMELHO",
"KETAMINA":"VERDE",
"ÁLCOOL":"VERMELHO",
"GHB":"VERMELHO",
"COCAÍNA":"PRETO",
"POPPERS":"AZUL",
"VIAGRA":"PRETO"
}
} 
  * {
"nomeDaSubstancia":"VIAGRA",
"cruzamentos":{
"MACONHA":"VERDE",
"LSD":"AMARELO",
"COGUMELO":"VERMELHO",
"CAFEÍNA":"AMARELO",
"MDMA":"AMARELO",
"KETAMINA":"VERDE",
"ÁLCOOL":"VERMELHO",
"GHB":"VERMELHO",
"COCAÍNA":"PRETO",
"POPPERS":"PRETO",
"VIAGRA":"AZUL"
}
}

[//]: # (### Modo execução testes:)

[//]: # (* [application.properties.xml]&#40;src%2Fmain%2Fresources%2Fapplication.properties&#41;:)

[//]: # (  * Determinar em spring.jpa.hibernate.ddl-auto desejado desejado)

[//]: # (  * Inlcuir usuario e senha)

[//]: # (* Rodar [Gs3ApplicationTests.java]&#40;src%2Ftest%2Fjava%2Fbr%2Fcom%2Fvaidaruim%2Fgs3%2FGs3ApplicationTests.java&#41;)
