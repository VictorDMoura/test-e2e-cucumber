🚀 Ruumy - Sistema de Agendamento de Salas (E2E & BDD)
Este projeto foi desenvolvido como parte da Formação Build & Run, focado em Testes de Integração e End-to-End (E2E) utilizando a abordagem BDD (Behavior Driven Development). O objetivo principal é garantir a qualidade dos fluxos de negócio de um sistema de reserva de salas de reunião (Ruumy), simulando o comportamento real do usuário através de automação robusta.

🛠️ Tecnologias Utilizadas
- Java 21: Linguagem base do projeto.
- Spring Boot 3.4.4: Framework para a construção da API e gestão do contexto de testes.
- Cucumber: Ferramenta para execução de cenários de teste escritos em Gherkin (BDD).
- RestAssured: Framework para automação e validação de APIs REST.
- JUnit 5 (Platform Suite): Para a orquestração e execução da suite de testes.
- H2 Database: Banco de dados em memória para testes rápidos e isolados.
- Maven: Gestor de dependências e build.

📑 O que foi implementado
O projeto está dividido em dois módulos principais:
- meeting-room-booking: A API (Microserviço) que contém as regras de negócio para listagem, reserva e cancelamento de salas.
- ruumye2e: O projeto de testes automatizados, onde os cenários de comportamento são validados.

Funcionalidades Testadas:

- Listagem de Salas: Validação se as salas cadastradas (América do Sul, Europa, etc.) estão disponíveis para o usuário.
- Reserva de Sala: Fluxo completo de reserva com sucesso e validação de conflitos de horário (Already Booked).
- Cancelamento (Unbook): Regra de negócio que permite cancelar reservas futuras, mas impede o cancelamento de reuniões que já iniciaram ou passaram.

🧪 Estrutura de Testes BDD
Os testes foram escritos em Gherkin, permitindo que as regras de negócio sejam legíveis para todo o time técnico e de produto:

```Gherkin
Scenario: Reservar uma sala disponível
  Given the room "Sala América do Sul" exists
  And the room has no bookings for today
  When I book the room for one hour from now
  Then them room should be successfully booked
```
Destaques Técnicos:
- ScenarioContext: Implementação de um contexto compartilhado para passar dados (como IDs de reserva e Responses) entre diferentes steps de um mesmo cenário.
- Test Utils: Criação de endpoints auxiliares (perfil de test) para limpeza de banco de dados entre cenários, garantindo o isolamento dos testes.
- JsonPath: Utilizado para extrair e validar informações específicas do corpo das respostas JSON retornadas pela API.

🚀 Como executar o projeto
  1. Clonar o repositório
  2. ubir a API:
  3. Navegue até a pasta meeting-room-booking.
  4. Execute ./mvnw spring-boot:run.
  5.Executar os testes E2E:
  6.Navegue até a pasta ruumye2e.
  7.Execute ./mvnw test.

O relatório será gerado em ruumye2e/target/cucumber-report.html.

Projeto desenvolvido durante a Formação Build & Run. Agradecimentos ao Bruno Garcia e equipe pelo conteúdo de excelência! 🌍💪

#buildrun #bdd #cucumber #restassured #qa #springboot #qualityassurance
