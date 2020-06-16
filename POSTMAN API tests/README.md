# SpringMVC2019
## Testowanie REST API używając aplikacji POSTMAN
Po uruchomieniu aplikacji Postman importujemy kolekcję
przygotowanych requestów.

<div align="center"><img alt="import-img" width="230" height="130" src="../img/import_1.png"></div>
<div align="center"><img alt="import-img" width="519" height="315" src="../img/import_2.png"></div>
Wybieramy plik z kolekcją requestów
<div align="center">
<img alt="import-img" width="504" height="200" src="../img/import_3.png">
</div>
Zatwierdzamy Import
<div align="center">
<img alt="import-img" width="526" height="315" src="../img/import_4.png">
</div>
Po zaimportowaniu, w panelu kolecji powinny być widoczne 29 nowe requesty
<div align="center">
<img alt="import-img" width="393" height="495" src="../img/import_5.png">
</div>

## Prezentacja kilku przykładowych testów:  
#### 1. Wyświetlenie wszystkich rekordów z tabeli 'Glowa' za pomocą requestu GET o nazwie '1.2.1 glowa getAll'

Wynik wykonanego zapytania:  
<img alt="postman-img" width="950" height="290" src="../img/1.1_glowa_getAll.png">  
W bazie danych sprawdzamy czy zwrócona odpowiedź na zapytanie jest zgodna z prawdą:  
<img alt="hsqldb-dbMAN-img" src="../img/1.2_glowa_HSQLdbMan.png">  
Tak - w bazie danych znajduje się dokładnie ten 1 rekord, który został zwrócony w odpowiedzi na nasze zapytanie GET.

#### 2. Wstawienie rekordu do tabeli 'Glowa' za pomocą requestu POST o nazwie '1.1 glowa wstawienie'

Przed wykonaniem zapytania POST wstawienia sprawdzimy stan tabeli 'Glowa' korzystając z zapytania GET z punktu 1:  
<img alt="postman-img" width="950" height="290" src="../img/1.1_glowa_getAll.png">  
W tabeli jest 1 rekord.  

Wynik wykonania zapytania POST wstawienia rekordu do tabeli 'Glowa':  
<img alt="postman-img" width="950" height="400" src="../img/2.1_glowa_Insert.png">  

Po wykonaniu zapytania POST wstawienia ponownie sprawdzamy stan tabeli 'Glowa'  
<img alt="postman-img" width="960" height="390" src="../img/2.2_glowa_getAll.png">  
Teraz w tabeli są 2 rekordy i wśród nich jest nowo dodany.  

#### 3. Wyświetlenie pojedynczego rekordu na podstawie podanego Id za pomocą zapytania GET o nazwie '1.2.2 glowa getById'

Wynik wykonania zapytania z parametrem Id==1:  
<img alt="postman-img" width="960" height="337" src="../img/3_getGlowaById.png">  

#### 4. Zmiana pojedynczego rekordu w tabeli 'Glowa' na podstawie Id za pomocą zapytania PUT o nazwie '1.3 glowa update'

Wynik wykonania zapytania PUT z parametrem Id==1:  
<img alt="postman-img" width="960" height="394" src="../img/4.1_glowaUpdate.png">  

Po wykonaniu zapytania PUT sprawdzamy stan rekordu z Id==1 w tabeli 'Glowa' korzystając 
z zapytania GET w punktu 3.  
<img alt="postman-img" width="960" height="338" src="../img/4.2_glowaPoUpdate.png">  
Jak widać, wartość pola 'srednia' została poprawnie zmieniona na nową.

#### 5. Skasowanie pojedynczego rekordu z tabeli 'Glowa' na podstawie Id za pomocą zapytania DELETE o nazwie '1.4 glowa delete'

Przed wykonaniem zapytania DELETE sprawdzamy stan tabeli 'Glowa' uzywajac zapytania GET z punktu 1:  
<img alt="postman-img" width="960" height="401" src="../img/5.1_getAll_before.png">  
W tabeli 'Glowa' znajdują się 2 rekordy - jeden z Id==1, a drugi z Id==2.

Wynik wykonania zapytania DELETE z parametrem Id==2:  
<img alt="postman-img" width="960" height="348" src="../img/5.2_glowaDelete.png">  

Po wykonaniu zapytania DELETE ponownie sprawdzamy stan tabeli 'Glowa':  
<img alt="postman-img" width="960" height="359" src="../img/5.3_getAll_after.png">  
Teraz w tabeli znajduje się tylko 1 rekord z Id==1.  


## Autor
- Jakub Skrzypiec (@jakub.skrzypiec - jakub.skrzypiec1@gmail.com)

