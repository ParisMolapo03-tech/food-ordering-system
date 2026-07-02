# RESEARCH

## Q1. What is JPA? What is Hibernate? How are they related?
       * JPA is a specification that defines how Java objects map to database tables. 
         Hibernate is the most popular implementation of JPA that actually does the work 
         of talking to the database. 

## Q2. What is the difference between @Entity and @Table?
       * @Entity marks a class as a database entity. @Table specifies the exact table 
          name in the database that the entity maps to.

## Q3. What is a foreign key? What is @ManyToOne? Give 2 real-world examples.
        *  A foreign key is a column that references the primary key of another table. @ManyToOne means 
           many records in one table relate to one record in another table.
        * Real world examples:
            - A menu item belongs to one category — many menus, one category
            - A student belongs to one school — many students, one school

## Q4. What does @JoinColumn(name = "category_id") do?
        * @JoinColumn specifies the name of the foreign key column in the database that links the
          two tables together.

## Q5. Why store price as BigDecimal and not double?
        * BigDecimal is used for money because it is exact. double has floating point rounding
          errors which can cause incorrect calculations when dealing with prices.

## Q6. What does FetchType LAZY vs EAGER mean? What is the default for @ManyToOne?
        *  LAZY loading only fetches related data when accessed. EAGER loading fetches everything immediately. 
           The default for @ManyToOne is EAGER.

## Q7. What is the N+1 query problem?
        *  The N+1 problem happens when fetching a list of entities causes N additional queries to load 
           related entities. It slows down the application significantly.

## Q8. What is dependency injection? Constructor injection vs field injection which is preferred and why?
        * Constructor injection is preferred over field injection because it allows 
          fields to be final, makes dependencies explicit, and is easier to test.

## Q9. What does @RequiredArgsConstructor (Lombok) do?
        *  @RequiredArgsConstructor generates a constructor for all private final fields automatically, 
           removing boilerplate constructor code.

## Q10. What is the role of the SERVICE layer? Why must it be separate from the controller?
        * The service layer contains business logic and must be separate from the controller so each layer has 
          one responsibility. The controller handles HTTP, the service handles the rules.

## Q11. Why MUST you validate that categoryId exists before saving a menu?
        * We validate categoryId before saving because if the category doesn't exist the database will throw 
          an error. It's better to catch this early and return a clean 404 response.

## Q12. Difference between save() and saveAndFlush()?
        * save() saves the entity but may delay writing to the database. saveAndFlush() saves and immediately
          writes to the database. save() is sufficient for most use cases.

## Q13. Why write private mapper methods (entity <-> dto)?
        * Private mapper methods avoid code repetition. Instead of writing the same conversion code multiple 
          times, you write it once and call it wherever needed.

SELF-QUIZ

## Q1. Why didn't we add @OneToMany on Category for menus? (think: bidirectional vs unidirectional relationships)
    * We only needed to access the category from a menu item, so we used a unidirectional relationship 
      with @ManyToOne. Adding @OneToMany on Category would make the relationship bidirectional,
      which adds extra complexity and wasn't required for this task.

## Q2. What would ddl-auto = create-drop do? When would you use it?
    * create-drop creates the database tables when the application starts and deletes them when it stops. It 
      is useful during development and testing, but it should not be used in production because 
      all data will be lost when the application shuts down.

## Q3. If you delete a Category that has menus, what happens by default?
    * By default, the database prevents the category from being deleted because the menu records still 
      reference it through a foreign key. This results in a foreign key constraint error unless cascading or 
      another delete strategy is configured.

## Q4. . Why is BigDecimal better than double for storing money values?
    * BigDecimal stores decimal values accurately without rounding errors, making it suitable for money 
      calculations. double can produce small precision errors because it stores numbers in
      binary floating-point format.




