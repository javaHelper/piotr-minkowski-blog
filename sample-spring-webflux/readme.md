# Using Reactive WebClient with Spring WebFlux

https://piotrminkowski.com/2019/11/04/using-reactive-webclient-with-spring-webflux/

- Run the test case

```logs
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.1)

13:48:46.635 --- [           main] : Starting SampleSpringWebFluxTest using Java 11.0.13 on Prateeks-MacBook-Pro.local with PID 38353 (started by prateekashtikar in /Users/prats/Documents/workspace/sample-spring-webflux)
13:48:46.637 --- [           main] : No active profile set, falling back to 1 default profile: "default"
13:48:48.682 --- [           main] : CPU: 8
13:48:49.630 --- [           main] : Exposing 1 endpoint(s) beneath base path '/actuator'
13:48:50.858 --- [           main] : Netty started on port 8080
13:48:50.884 --- [           main] : Started SampleSpringWebFluxTest in 4.994 seconds (JVM running for 7.39)
13:48:52.159 --- [     parallel-1] : Server produces: Person(id=1, firstName=Name01, lastName=Surname01, age=11)
13:48:52.250 --- [ctor-http-nio-3] : Client subscribes: Person(id=1, firstName=Name01, lastName=Surname01, age=11)
13:48:52.298 --- [     parallel-1] : Server produces: Person(id=2, firstName=Name02, lastName=Surname02, age=22)
13:48:52.299 --- [     parallel-1] : Server produces: Person(id=3, firstName=Name03, lastName=Surname03, age=33)
13:48:52.299 --- [     parallel-1] : Server produces: Person(id=4, firstName=Name04, lastName=Surname04, age=44)
13:48:52.300 --- [     parallel-1] : Server produces: Person(id=5, firstName=Name05, lastName=Surname05, age=55)
13:48:52.300 --- [     parallel-1] : Server produces: Person(id=6, firstName=Name06, lastName=Surname06, age=66)
13:48:52.300 --- [     parallel-1] : Server produces: Person(id=7, firstName=Name07, lastName=Surname07, age=77)
13:48:52.300 --- [     parallel-1] : Server produces: Person(id=8, firstName=Name08, lastName=Surname08, age=88)
13:48:52.300 --- [ctor-http-nio-3] : Client subscribes: Person(id=2, firstName=Name02, lastName=Surname02, age=22)
13:48:52.300 --- [     parallel-1] : Server produces: Person(id=9, firstName=Name09, lastName=Surname09, age=99)
13:48:52.300 --- [ctor-http-nio-3] : Client subscribes: Person(id=3, firstName=Name03, lastName=Surname03, age=33)
13:48:52.302 --- [ctor-http-nio-3] : Client subscribes: Person(id=4, firstName=Name04, lastName=Surname04, age=44)
13:48:52.304 --- [ctor-http-nio-3] : Client subscribes: Person(id=5, firstName=Name05, lastName=Surname05, age=55)
13:48:52.304 --- [ctor-http-nio-3] : Client subscribes: Person(id=6, firstName=Name06, lastName=Surname06, age=66)
13:48:52.304 --- [ctor-http-nio-3] : Client subscribes: Person(id=7, firstName=Name07, lastName=Surname07, age=77)
13:48:52.305 --- [ctor-http-nio-3] : Client subscribes: Person(id=8, firstName=Name08, lastName=Surname08, age=88)
13:48:52.306 --- [ctor-http-nio-3] : Client subscribes: Person(id=9, firstName=Name09, lastName=Surname09, age=99)
13:48:52.352 --- [ctor-http-nio-4] : Server Produces : Person(id=1, firstName=Name01, lastName=Surname01, age=11)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=2, firstName=Name02, lastName=Surname02, age=22)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=3, firstName=Name03, lastName=Surname03, age=33)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=4, firstName=Name04, lastName=Surname04, age=44)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=5, firstName=Name05, lastName=Surname05, age=55)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=6, firstName=Name06, lastName=Surname06, age=66)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=7, firstName=Name07, lastName=Surname07, age=77)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=8, firstName=Name08, lastName=Surname08, age=88)
13:48:52.353 --- [ctor-http-nio-4] : Server Produces : Person(id=9, firstName=Name09, lastName=Surname09, age=99)
13:48:52.361 --- [ctor-http-nio-3] : Client subscribes: Person(id=1, firstName=Name01, lastName=Surname01, age=11)
13:48:52.364 --- [ctor-http-nio-3] : Client subscribes: Person(id=2, firstName=Name02, lastName=Surname02, age=22)
13:48:52.365 --- [ctor-http-nio-3] : Client subscribes: Person(id=3, firstName=Name03, lastName=Surname03, age=33)
13:48:52.365 --- [ctor-http-nio-3] : Client subscribes: Person(id=4, firstName=Name04, lastName=Surname04, age=44)
13:48:52.365 --- [ctor-http-nio-3] : Client subscribes: Person(id=5, firstName=Name05, lastName=Surname05, age=55)
13:48:52.365 --- [ctor-http-nio-3] : Client subscribes: Person(id=6, firstName=Name06, lastName=Surname06, age=66)
13:48:52.368 --- [ctor-http-nio-3] : Client subscribes: Person(id=7, firstName=Name07, lastName=Surname07, age=77)
13:48:52.369 --- [ctor-http-nio-3] : Client subscribes: Person(id=8, firstName=Name08, lastName=Surname08, age=88)
13:48:52.369 --- [ctor-http-nio-3] : Client subscribes: Person(id=9, firstName=Name09, lastName=Surname09, age=99)
13:48:52.496 --- [     parallel-2] : Server produces: Person(id=1, firstName=Name01, lastName=Surname01, age=11)
13:48:52.602 --- [ctor-http-nio-3] : Client subscribes: Person(id=1, firstName=Name01, lastName=Surname01, age=11)
13:48:52.607 --- [     parallel-3] : Server produces: Person(id=2, firstName=Name02, lastName=Surname02, age=22)
13:48:52.707 --- [ctor-http-nio-3] : Client subscribes: Person(id=2, firstName=Name02, lastName=Surname02, age=22)
13:48:52.715 --- [     parallel-4] : Server produces: Person(id=3, firstName=Name03, lastName=Surname03, age=33)
13:48:52.812 --- [ctor-http-nio-3] : Client subscribes: Person(id=3, firstName=Name03, lastName=Surname03, age=33)
13:48:52.821 --- [     parallel-5] : Server produces: Person(id=4, firstName=Name04, lastName=Surname04, age=44)
13:48:52.920 --- [ctor-http-nio-3] : Client subscribes: Person(id=4, firstName=Name04, lastName=Surname04, age=44)
13:48:52.928 --- [     parallel-6] : Server produces: Person(id=5, firstName=Name05, lastName=Surname05, age=55)
13:48:53.027 --- [ctor-http-nio-3] : Client subscribes: Person(id=5, firstName=Name05, lastName=Surname05, age=55)
13:48:53.031 --- [     parallel-7] : Server produces: Person(id=6, firstName=Name06, lastName=Surname06, age=66)
13:48:53.132 --- [ctor-http-nio-3] : Client subscribes: Person(id=6, firstName=Name06, lastName=Surname06, age=66)
13:48:53.141 --- [     parallel-8] : Server produces: Person(id=7, firstName=Name07, lastName=Surname07, age=77)
13:48:53.240 --- [ctor-http-nio-3] : Client subscribes: Person(id=7, firstName=Name07, lastName=Surname07, age=77)
13:48:53.247 --- [     parallel-1] : Server produces: Person(id=8, firstName=Name08, lastName=Surname08, age=88)
13:48:53.343 --- [ctor-http-nio-3] : Client subscribes: Person(id=8, firstName=Name08, lastName=Surname08, age=88)
13:48:53.353 --- [     parallel-2] : Server produces: Person(id=9, firstName=Name09, lastName=Surname09, age=99)
13:48:53.449 --- [ctor-http-nio-3] : Client subscribes: Person(id=9, firstName=Name09, lastName=Surname09, age=99)
```

<img width="584" alt="Screenshot 2023-01-03 at 1 50 51 PM" src="https://user-images.githubusercontent.com/54174687/210321320-fce612ba-89f9-417d-84ca-a94a97be1093.png">

<img width="549" alt="Screenshot 2023-01-03 at 1 51 19 PM" src="https://user-images.githubusercontent.com/54174687/210321335-09ceaad1-94fe-4540-aa70-69754e079da1.png">


