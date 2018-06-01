Scenario: LoginScenario

Given Mam webdrivera
When Zaloguje sie loginem <a> i haslem <b>
Then Otrzymam taki napis <wynik>

Examples:
|a              |b        |wynik          |
|testtest@wp.pl |Test123! |testtest@wp.pl |
|useruser@wp.pl |User123! |useruser@wp.pl |
