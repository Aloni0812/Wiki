# Wiki Data
the service should accept some kind of term/word and return the search result of the text from wikipedia corresponding to the word
results should be requested from the wikipedia API
## Task
Basic REST service 
1. Create and run locally the simplest web/REST service using any open source example using Java stack: Spring (Spring Boot)/maven/gradle/Jersey/ Spring MVC. 
2. Add a GET endpoint that accepts input parameters as queryParams in the URL according to the option, and returns any hard-coded result in the form of JSON according to the option.
## Sonarcloud
https://sonarcloud.io/summary/new_code?id=Aloni0812_Wiki2
## Realization
- WikiController: This class is a Spring MVC controller that handles incoming HTTP requests related to a wiki application, such as creating, editing, or deleting wiki pages.
- InMemoryWikiServiceImpl: This class implements the WikiService interface and provides business logic for managing wiki pages using an in-memory database.
- Wiki: This class represents a wiki page with attributes such as title, content, and author.
- InMemoryWikiDAO: This class is a data access object that provides methods to interact with an in-memory database to store and retrieve wiki page information.
- WikiService: This interface defines methods for managing wiki pages, such as creating a new page, editing existing pages, or retrieving page information.
