
# Sistema de Locadora de Automóveis

## Descrição

Este projeto é um sistema para gerenciar o aluguel de automóveis. Ele permite que clientes se cadastrem, escolham veículos disponíveis para aluguel, e efetivem a reserva de forma prática e eficiente. O sistema foi projetado para atender às necessidades de uma locadora de automóveis, fornecendo funcionalidades essenciais para o gerenciamento de clientes, veículos e aluguéis.

## Funcionalidades

### Cadastro de Clientes
- **Descrição**: Permite que clientes em potencial se cadastrem na locadora para acessar os serviços de aluguel.
- **Critérios de Aceitação**:
  - Formulário de cadastro na página inicial.
  - Solicitação de informações básicas como nome completo, data de nascimento, CPF, número da CNH e e-mail.
  - Confirmação na tela após cadastro bem-sucedido.
  - Verificação de validade do e-mail para evitar registros duplicados.
  - Redirecionamento para a página inicial após o cadastro.

### Escolha de Veículo para Aluguel
- **Descrição**: Clientes cadastrados podem escolher entre os veículos disponíveis para aluguel.
- **Critérios de Aceitação**:
  - Seção "Seleção de Veículos" na página inicial.
  - Lista ou grade com informações do veículo: fabricante, modelo, categoria, acessórios, preço por dia.
  - Aplicação de filtros por categoria de veículo e acessórios.
  - Página de detalhes do veículo com especificações técnicas e descrição.
  - Seleção de período de aluguel especificando datas de início e término.
  - Adição do veículo ao carrinho de aluguel.
  - Resumo do carrinho com veículos selecionados, datas de aluguel e custo total estimado.
  - Revisão, ajustes e confirmação da reserva.

### Efetivação do Aluguel de Veículo
- **Descrição**: Finaliza o processo de aluguel com a confirmação da reserva e pagamento.
- **Critérios de Aceitação**:
  - Página de resumo da reserva com detalhes do veículo, datas e custo total.
  - Revisão e concordância com os termos e condições do aluguel.
  - Escolha do método de pagamento e inserção das informações do cartão de crédito (simulado).
  - Confirmação do pagamento e finalização do aluguel.
  - Bloqueio das datas de aluguel no calendário e marcação do veículo como "reservado".
  - Recebimento de confirmação na tela e por e-mail com detalhes do aluguel.

## Instalação

### Pré-requisitos

- Java (versão 21)
- Spring Boot 
- Docker

### Passos de Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/FredyHG/desafio-locadora-solutis.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd seu_projeto
   ```
3. Com o Docker aberto, execute no terminal:
   ```bash
   docker-compose up --build
   ```

## Uso

### Todos os endpoint podem ser acessados no link abaixo após a aplicação ser rodada

```bash
http://localhost:8080/swagger-ui/index.html
```

## Contribuidores

- Fredy Gomes
- Syllas Braga
- Pietra Costa
- Luiz Casais
- Maria Eduarda Ramalho
- Renato Gonçalves
