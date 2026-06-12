# PadroesProjetoEngenhariaSoftware - Exemplos em Java

Este repositório foi criado como trabalho da disciplina de Engenharia de Software, com o objetivo de demonstrar a aplicação de **três padrões de projeto** (um Criacional, um Estrutural e um Comportamental), com base no catálogo do site **Refactoring Guru**:

🔗 https://refactoring.guru/pt-br/design-patterns

Todas as explicações de contexto, problema e solução apresentadas abaixo foram baseadas no conteúdo do Refactoring Guru, adaptado e resumido para fins didáticos. Os créditos do conteúdo original de referência pertencem ao site Refactoring Guru.

## Sobre o uso de LLM

Os códigos de exemplo deste repositório foram implementados com o auxílio do **Claude (Anthropic)**, utilizado como ferramenta de apoio para gerar e revisar os exemplos de código em Java.

## Estrutura do repositório

```
design-patterns-java/
├── factory_method/
│   ├── Notification.java
│   ├── EmailNotification.java
│   ├── SMSNotification.java
│   ├── PushNotification.java
│   ├── NotificationFactory.java
│   ├── EmailNotificationFactory.java
│   ├── SMSNotificationFactory.java
│   ├── PushNotificationFactory.java
│   └── Main.java
├── adapter/
│   ├── TemperatureSensor.java
│   ├── LegacyFahrenheitSensor.java
│   ├── FahrenheitToCelsiusAdapter.java
│   └── Main.java
├── strategy/
│   ├── ShippingStrategy.java
│   ├── StandardShipping.java
│   ├── ExpressShipping.java
│   ├── PickupShipping.java
│   ├── Order.java
│   └── Main.java
└── README.md
```

---

## 1. Factory Method (Padrão Criacional)

### Contexto e problema

O Factory Method é um padrão criacional que fornece uma interface para criar objetos em uma superclasse, mas permite que as subclasses alterem o tipo de objetos que serão criados.

Imagine um sistema que precisa enviar **notificações** para os usuários. No início, o sistema só enviava e-mails, então toda a lógica de criação de `EmailNotification` está espalhada pelo código. Com o tempo, surge a necessidade de enviar notificações também por **SMS** e **Push**. Se a criação dos objetos estiver "hardcoded" (com `new EmailNotification()` em vários lugares), cada nova forma de notificação exigiria alterar várias partes do código, violando o princípio de aberto/fechado.

### Solução

O Factory Method propõe substituir as chamadas diretas de criação de objetos (`new`) por chamadas a um **método fábrica** especial. Os objetos ainda são criados via `new`, mas isso é feito dentro do método fábrica. As subclasses podem sobrescrever esse método para alterar a classe dos objetos criados, desde que todos eles sigam uma interface comum.

### Código explicado (pasta `factory_method/`)

- **`Notification.java`**: interface comum (Produto), define o método `send(String message)`.
- **`EmailNotification.java`**, **`SMSNotification.java`**, **`PushNotification.java`**: implementações concretas (Produtos Concretos), cada uma com sua própria forma de "enviar" a mensagem via `System.out.println`.
- **`NotificationFactory.java`**: classe abstrata Criadora, declara o **Factory Method** `protected abstract Notification createNotification()` e o método `notify(String message)`, que usa o produto criado sem saber qual classe concreta foi instanciada.
- **`EmailNotificationFactory.java`**, **`SMSNotificationFactory.java`**, **`PushNotificationFactory.java`**: Criadores Concretos, cada um implementa `createNotification()` retornando o tipo de notificação correspondente.
- **`Main.java`**: código cliente. Cria uma lista de `NotificationFactory` (uma de cada tipo) e chama `notify(...)` em cada uma, sem precisar saber qual classe concreta de notificação está sendo usada internamente.

**Como compilar e executar:**
```bash
cd factory_method
javac *.java
java Main
```

**Saída esperada:**
```
[Email] Enviando e-mail com a mensagem: Seu pedido foi confirmado!
[SMS] Enviando SMS com a mensagem: Seu pedido foi confirmado!
[Push] Enviando notificação push: Seu pedido foi confirmado!
```

**Benefício prático:** se for necessário criar um novo tipo de notificação (por exemplo, WhatsApp), basta criar uma nova classe de produto (`WhatsAppNotification`) e uma nova fábrica (`WhatsAppNotificationFactory`), sem alterar o código já existente.

---

## 2. Adapter (Padrão Estrutural)

### Contexto e problema

O Adapter é um padrão estrutural que permite que objetos com interfaces incompatíveis colaborem entre si.

Imagine que um sistema novo de monitoramento foi projetado para trabalhar exclusivamente com sensores de temperatura que retornam valores em **graus Celsius**, através de uma interface padronizada `TemperatureSensor`. Porém, a empresa já possui um sensor legado (`LegacyFahrenheitSensor`) que só retorna a temperatura em **graus Fahrenheit**, e reescrever esse sensor não é viável.

O sistema novo não pode simplesmente usar o sensor legado diretamente, pois as interfaces (e as unidades de medida) são incompatíveis.

### Solução

O Adapter atua como um "tradutor" entre as duas interfaces: ele implementa a interface que o sistema novo espera (`TemperatureSensor`) e, internamente, encapsula um objeto da classe legada (`LegacyFahrenheitSensor`), convertendo a chamada e o valor retornado para o formato esperado.

### Código explicado (pasta `adapter/`)

- **`TemperatureSensor.java`**: interface (alvo) que o sistema novo conhece e utiliza, com o método `getTemperatureCelsius()`.
- **`LegacyFahrenheitSensor.java`**: classe legada (adaptada), incompatível, pois só possui o método `getTemperatureFahrenheit()`.
- **`FahrenheitToCelsiusAdapter.java`**: o **Adapter**. Implementa a interface `TemperatureSensor`, recebe no construtor uma instância de `LegacyFahrenheitSensor` e, no método `getTemperatureCelsius()`, chama o sensor legado e converte o valor de Fahrenheit para Celsius usando a fórmula `(F - 32) * 5 / 9`.
- **`Main.java`**: código cliente. O método `displayTemperature(TemperatureSensor sensor)` só conhece a interface `TemperatureSensor` e não sabe (nem precisa saber) que, por trás do adapter, existe um sensor legado em Fahrenheit.

**Como compilar e executar:**
```bash
cd adapter
javac *.java
java Main
```

**Saída esperada:**
```
Temperatura atual: 37.00°C
```

**Benefício prático:** o sensor legado continua funcionando sem nenhuma alteração em seu código, e o sistema novo pode utilizá-lo normalmente através da interface `TemperatureSensor`, graças ao adapter.

---

## 3. Strategy (Padrão Comportamental)

### Contexto e problema

O Strategy é um padrão comportamental que permite definir uma família de algoritmos, colocar cada um deles em uma classe separada e tornar seus objetos intercambiáveis.

Imagine um sistema de e-commerce que precisa calcular o **valor do frete** de um pedido. Existem diferentes formas de calcular esse frete: frete padrão, frete expresso e retirada na loja (sem custo). Se toda essa lógica for colocada dentro de uma única classe `Order`, usando vários `if/else`, a classe cresce muito, fica difícil de testar e qualquer novo tipo de frete exige alterar essa classe inteira, o que aumenta o risco de introduzir bugs.

### Solução

O Strategy propõe extrair cada algoritmo (cada forma de calcular o frete) para sua própria classe, chamada de "estratégia", todas implementando uma interface comum. A classe principal (`Order`) passa a ter apenas uma referência para um objeto de estratégia e delega o cálculo para ele. Assim, é possível trocar o algoritmo usado em tempo de execução, apenas alterando qual estratégia está sendo referenciada.

### Código explicado (pasta `strategy/`)

- **`ShippingStrategy.java`**: interface comum, declara o método `calculate(double weight, double distance)`.
- **`StandardShipping.java`**, **`ExpressShipping.java`**, **`PickupShipping.java`**: as **estratégias concretas**, cada uma implementando uma fórmula diferente para o cálculo do frete.
- **`Order.java`**: o **contexto**. Armazena uma referência para uma estratégia (`ShippingStrategy strategy`) e delega o cálculo do frete para ela através do método `getShippingCost()`. O método `setStrategy(...)` permite trocar a estratégia em tempo de execução.
- **`Main.java`**: cria um pedido (`Order`) com a estratégia `StandardShipping`, depois troca para `ExpressShipping` e, por fim, para `PickupShipping`, mostrando como o mesmo objeto `Order` pode usar diferentes algoritmos de cálculo sem que sua estrutura precise ser alterada.

**Como compilar e executar:**
```bash
cd strategy
javac *.java
java Main
```

**Saída esperada:**
```
Frete padrão:   R$ 12.50
Frete expresso: R$ 45.00
Frete retirada: R$ 0.00
```

**Benefício prático:** novas formas de calcular frete podem ser adicionadas criando apenas uma nova classe que implemente `ShippingStrategy`, sem necessidade de modificar a classe `Order`.

---

## Como executar os exemplos

Cada padrão está em sua própria pasta e possui uma classe `Main` independente. É necessário ter o **JDK (Java 11+)** instalado.

```bash
# Factory Method
cd factory_method
javac *.java
java Main

# Adapter
cd ../adapter
javac *.java
java Main

# Strategy
cd ../strategy
javac *.java
java Main
```

## Referências

- REFACTORING GURU. **Padrões de Projeto**. Disponível em: https://refactoring.guru/pt-br/design-patterns. Acesso em: 2026.
- REFACTORING GURU. **Factory Method**. Disponível em: https://refactoring.guru/pt-br/design-patterns/factory-method.
- REFACTORING GURU. **Adapter**. Disponível em: https://refactoring.guru/pt-br/design-patterns/adapter.
- REFACTORING GURU. **Strategy**. Disponível em: https://refactoring.guru/pt-br/design-patterns/strategy.
