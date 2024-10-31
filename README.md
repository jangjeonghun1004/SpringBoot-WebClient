# WebClient

    WebClient는 RestTemplate의 지원 중단에 따라 이를 대체할 수 있는 수단임과 동시에
    다양한 많은 기능을 추가적으로 제공한다.

    - 논블로킹(Non-Blocking) I/O를 지원합니다.
    - 리액티브 스트림(Reactive Streams)의 백 프레셔(Back Pressure)를 지원합니다.
    - 작은 하드웨어 리소스로 동시성을 지원합니다.
    - 함수형 AIP를 지원합니다.
    - 동기, 비동기 상호작용을 지원합니다.
    - 스트리밍을 지원합니다.

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
        <version>3.3.4</version>
    </dependency>

# WebClient 구현

    retrieve(): 요청에 대한 응답을 받습니다.
    bodyToMono(): 리턴 타입을 설정합니다.
    block(): 블로킹 형식으로 동작하게끔 설정합니다.
    toEntity(): ResponseEntity 타입으로 응답을 받는다.
    exchangeToMono(): 응답 코드 결과에 따라 다르게 응답을 설정할 수 있습니다.
