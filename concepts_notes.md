
1) 
✅ What is a Singleton Bean?   

In Spring, when you annotate a class with @Service, @Component, @Repository, or @Controller,@Configuration
@RestController

 Spring creates a bean (object) for that class and manages it.

By default, this bean is a Singleton, which means:

⚠️ Only one instance of that class is created and shared across the entire application.(used for injection,method calling)

2) @autowire is dependency injection

3) Serialization just means:

👉 “Turning a Java object into JSON (or text) so it can be sent to the browser or API response.”

This is exactly what Swagger or any REST API does — it takes your Java object, and converts (serializes) it into JSON so the client can understand it.

And this work is done by a library like Jackson in the background.

4) 
JPA	is the law that says "Every car must have brakes, engine, etc."
Hibernate is a real car company (like Toyota) that builds cars following that law

and hibernate does jobs like understand those annotations and talks with the database it transforms the java objects into table rows and also retransforms table rows into java objects


5) 
✅ Do we need @Repository on interface-based repositories?
No, not required if you're extending JpaRepository — because:

Spring Data JPA automatically detects any interface that extends JpaRepository and registers it as a Spring Bean, behind the scenes.


