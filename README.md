# Chat-GPT-API
`chat-gpt-api` is a Spring Boot based Java library that provides a simple, easy-to-use interface for interacting with the ChatGPT API. It enables developers to send messages and receive generated completions from the ChatGPT models provided by OpenAI.

## Features
- Implements the ChatGPT API using Java and Spring Boot
- Provides a simple interface to interact with the ChatGPT API
- Supports different GPT models (GPT-3.5, GPT-4, etc.)
- Easy integration with your Spring Boot application

## Getting Started

### Prerequisites
- JDK 17 or higher
- Apache Maven 3.6.0 or higher (if building from source)

### Installation
To use `chat-gpt-api` in your project, you can add it as a dependency in your `pom.xml` file:

```xml
<dependency>
    <groupId>com.shahabkondri</groupId>
    <artifactId>chat-gpt-api</artifactId>
    <version>1.0.2</version>
</dependency>
```

To use a snapshot version of the library, add the following repository to your `pom.xml` file:
```xml
<repositories>
    <repository>
        <id>ossrh</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.shahabkondri</groupId>
    <artifactId>chat-gpt-api</artifactId>
    <version>1.0.3-SNAPSHOT</version>
</dependency>
```

### Configuration
Create an `application.properties` or `application.yml` file in your project's src/main/resources directory, and add the following property:

```properties
openai.apiKey=your_openai_api_key
```
Replace `your_openai_api_key` with your actual OpenAI API key.

## Usage
You can use `chat-gpt-api` to send messages and receive completions from the ChatGPT API.

First, create a `ChatGptRequest` object with the required parameters, such as the model and the list of messages:

```java
TextCompletionModel model = TextCompletionModel.GPT_3_5_TURBO;
List<Message> messages = List.of(new Message(MessageRole.USER, "tell me a joke"));

ChatGptRequest request = new ChatGptRequest(model, messages);
```

Then, use the `ChatGptClient` to send the request and receive completions:

```Java
@Autowired
private ChatGptClient chatGptClient;

public void chat() {
    Flux<ChatGptResponse> completions = chatGptClient.completions(request);
    completions.filter(response -> response.choices().get(0).delta().content() != null)
        .map(response -> response.choices().get(0).delta().content())
        .subscribe(System.out::println);
}
```
The `chat` method will print the generated completion, e.g., the response to the "tell me a joke" message.

## Documentation
You can find detailed documentation for each class and method in the Javadocs provided in the source code.

## Contributing
Contributions are welcome! Please feel free to submit issues and pull requests to improve the project.

## License
This project is licensed under the MIT License. See the LICENSE file for details.