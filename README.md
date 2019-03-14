# JavaProject
Implementation of Data Frame in Java as a project for Object-oriented programming course


Opis projektu:


Klasa DataFrame:
- opis: pozwala na przechowywanie dowolnej ilości danych, dowolnego typu (predefiniowanego wcześniej) w formacie kolumnowym (analogicznie jak w bazie danych)

- wymagania:
*kolumny powinny być przechowywane jako ArrayList

- metody:
*size() – zwracającą ilość wierszy w całej DF (uwaga – DF nie może mieć jednej z kolumn dłuższej niż pozostałe
*get(String colname) – zwracającą kolumnę o podanej nazwie
*get(String [] cols, boolean copy) – zwracającą nową DataFrame z kolumnami podanymi jako parametry. W zależności od wartości parametru copy albo tworzona jest głęboka kopia, albo płytka.
*iloc(int i) – zwracającą wiersz o podanym indeksie (jako nową DataFrame)
*iloc(int from, int to) – zwracającą nową DataFrame z wierszami z podanego zakresu
*readingFromFileFunction(String path, boolean header)- możliwość wczytywania danych z pliku (jesli header==true oznacz to że pierwsza linia w pliku to nagłówek. Jeśli header==false to znaczy że należy podać kolejny argument z nazwami kolumn. Domyślnie header==true)
* groupby(Sting[] colnames) - zwraca strukturę (Grupator) zawierającą małe DataFrames powstałe po grupowaniu względem kolumn. Struktura ta powinna implementować interfejs Groupby 



Klasa SparseDataFrame:
-opis: (reprezentacja macierzy rzadkiej) dziedziczy po DataFrame, ale przyjmuje inny paradygmat przechowywania danych. Dane nadal przechowywane są w kolumnach, ale tylko wartości różne od podanej w parametrze. Zakładamy, że wszystkie kolumny mają ten sam typ! 
Innymi słowy to co robi, to przechowuje indeksy niezerowych elementów i ich wartość (stwórz osobną klasę COOValue) na ta potrzebę.

Np. kolumna 0,3,0,0,0,0,0,1,0,0 dla parametru hide=„0” będzie reprezentowana jako dwie wartości COOValue(1,3), CooValue(7,1).

- wymagania:
*SparseDataFrame dziedziczy po DataFrame i implementuje wszystkie metody wcześniejsze.
*SparseDataFrame może w konstruktorze przyjąć jako parametr DataFrame i przekonwertować ją do postaci SparseDataFrame.
*Klasa COOValue jest immutable.
*Dodaj metodę toDense, która zwraca DataFrame (konwertuje SparseDataFrame do DataFrame)


Klasa abstrakcyjna Value:
-opis: stworzona po to aby kolumny nie musiały przechowywać typów Object, lub z góry okreslone typów

- metody:
*public abstract String toString()
*public abstract Value add(Value)
*public abstract Value sub(Value) -odejmowanie 
*public abstract Value mul(Value) -mnożenie
*public abstract Value div(Value) -dzielenie
*public abstract Value pow(Value) -potęgowanie
*public abstract boolean eq(Value)
*public abstract boolean lte(Value) -mniejsze lub równe
*public abstract boolean gte(Value) - większe lub równe
*public abstract boolean neq(Value) 
*public abstract boolean equals(Object other)
*public abstract int hashCode()
*public abstract Value create(String s) – tworzy konkretny obiekt ze stringa (wartość obiektu jest podana jako string)

Klasy IntegerValue, DoubleValue, FloatValue, StringValue, DateTimeValue:
- opis:  klasy dziedziczące po Value, implementujące konkretne wartości: Integer, Double, Float, String, DateTime

-wymagania
*klasy ...Value implementują interfejs Cloneable


Interfejs Applyable z metodą DataFrame apply(DataFrame) :

-opis: aplikującą pewną operację do całej DataFrame podanej jako parametr. Będzie on wykorzystywany przez interfejs Groupby. Chodzi o to, że zestaw funkcji oferowany przez Groupby nie jest duży i nie uwzględnia chociażby mediany, modlanej i innych, nie mówiąc o niestandardowych funkcjach użytkownika. W związku z tym nasz system powinien umożliwiać wykonanie dowolnej operacji na grupie wyodrębnionej w procesie groupby(). Ta operacja będzie implementowana jako konkretna klasa implementująca interfejs Applyable i potem mogła być przekazana jako operacja groupby.


Interfejs Groupby z operacjami:
*DataFrame max() – zwraca maksymalną wartość z poszczególnych kolumn w grupie, za wyjatkiem tych względem których grupowaliśmy.
*DataFrame min() – analogicznie j.w.
*DataFrame mean() – analogicznie j.w
*DataFrame std() – analogicznie j.w. (odchylenie standardowe )
*DataFrame sum() – analoginczie j.w
*DataFrame var() – analogicznie j.w. (wariancja)
*DataFrame apply(Applayable) – aplikuję operację implementowana przez Applyable do wszystkich grup z osobna


Interfejs graficzny (robiony w JavaFx):
*możliwość przedstawienia podstawowych statystyk dla całej DataFrame kolumn (min, max, std, var, mean – dla każdej kolumny z osobna)
*możliwość rysowania wykresów 2D. Użytkownik powinien mieć możliwość wybory dwóch kolumn, które stają się wówczas osiami wykresu.
*wyjątki przechwytywane na poziomie inetrfejsu i w razie potrzeby informacje oblędach raportowane użytkownikowi.
*możliwość rysowania scatter plot oraz bar chart na dowolnym poziomie skomplikowania i możliwości konfiguracji.


Klasa DataFrameDB:
- opis: umożliwia opcje wczytywania i zapisu data frame do bazy danych (najprościej SQLite). Stwórz w tym celu, dziedziczy po DataFrame

-wymagania:
*DataFrameDB może zwrócić obiekt DataFrame, który nie będzie już zależny od bazy danych.
*metodę statyczna, która wyniki dowolnego zapytania typu „SELECT” będzie konwertowała na obiekt DataFrame (nie DataFrameDB)

-zmiany w kodzie:
*implementacja operacji min i max, tak aby korzystały z silnika bazodanowego,analogicznie w przypadku groupby




Do wykonania:

*Zmodyfikuj DataFrame, tak aby obliczenia, które można zrównoleglić były zrównoleglone.
*Zrób testy rozwiązania: zwykłego, z bazą danych i wielowątkowego. Zapisz wyniki w formacie CSV a następnie użyj swojego GUI do wizualizowania wyników
*Poza metodami, których zrównoleglenie zależeć będzie od przybranej strategii implementacji, warunkiem koniecznym do zaliczenia zadania jest implementacja równoległego grupby wraz z operacjami: operacja dla każdej grupy liczona jest w osobnym wątku.

*Zmodyfikuj projekt w taki sposób, aby operacje groupby i inne możilwe były wykonywane na osobnych maszynach:
Konieczne będzie napisanie dwóch nowych komponentów:
-serwera pośredniczącego, który będzie nawiązywał połączenie i pośredniczył w 		wymianie danych
-serwerów obliczeniowych, które będą podłączać się do serwera głównego i dzięki temu ten będzie wiedział ile ma węzłów obliczeniowych. Powinny one wykorzystywać DataFrame do wykonywania obliczeń (zrównoleglonych)
-klientów (czyli naszych DataFrame), które będą łączyć się z serwerem pośredniczącym, i będą wysyłać mu żądanie operacji, wraz z porcja danych do obsłużenia.
*Wykonaj testy wszystkich rozwiązań na poniższych danych i przedstaw wyniki w formie wykresów (nie musi to być GUI – może byc Python, Excel, Matlab):
Zbiory danych do testów: TBA

Link do strony z laboratoriami, w każdym laboratorium znajduje się opis kolejnych części projektu:
http://home.agh.edu.pl/~sbobek/old/doku.php?id=progob

Link do mojego githuba na którym znajduje się projket:
https://github.com/BittenCookie22/Java-Lab/tree/second/DataFrame/src/DF
