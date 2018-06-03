Scenario: LoginScenario

Given Mam strone customera
When Nic nie wpisze
Then Tabela zostanie pełna

Given Mam driver
When Wejde na strone glowna
Then Tytul bedzie poprawny

Given Mam chromedrivera
When Zaloguje sie i przejde do seller
Then Wyskoczy access denied

Given Mam chromedriveraa
When Zaloguje sie i przejde do customer
Then Nie bedzie edycji itp

Scenario: NewOne

Given Mam dane:
|login|pass|
|testtest@wp.pl|Test123!|
Then Napis jest:
|message|
|testtest@wp.pl|

Scenario: ExamplesScenario

Given Mam webdrivera
When Zaloguje sie loginem <a> i haslem <b>
Then Otrzymam taki napis <wynik>

Examples:
|a              |b        |wynik          |
|testtest@wp.pl |Test123! |testtest@wp.pl |
|useruser@wp.pl |User123! |useruser@wp.pl |
