Proiect Etapa 1 POO TV
Costin Didoaca 323 CA

*******************************************************************************

- In acest proiect am reusit sa combin design pattern-urile Singleton si Visitor,
folosind Singleton pentru paginile de pe platforma (package platformpages), baze
de date si datele curente (package currentinfo) si Visitor pentru realizarea
actiunilor (package visitorpattern), fie ele "change page" sau "on page". De asemenea
am creat clasele de input (package iofiles) cu ajutorul carora vom prelua datele
de intrare in Main si le vom pasa mai departe bazelor de date. Apoi vom parsa
actiunile si in functie de tipul actiunii cerute vom folosi unul din visitorii
creati care sa realizeze actiunea de pe o pagina curenta specifica. Dupa parsarea
tuturor actiunilor se afiseaza output-ul si se golesec bazele de date si datele
curente pentru a lasa platforma curata (ca la inceput) pentru urmatorul test.
ChangingPageVisitor contine metodele doActionFrom*page*(*page type* page, Action
action) care verifica si face, in caz ca e permisa, actiunea de change page.
Toate aceste metode intorc un boolean care ne indica daca actiunea a fost
realizata cu succes sau nu (in functie de asta adaugam un nod specific in output).
Metoda tryToChangePage este universala pentru orice pagina si le include pe toate
cele explicate mai devreme.

- DoingOnPageActionsVisitor contine acelasi set de metode ca la visitorul pentru
actiunile change page, dar de data asta metodele sunt menite sa verifice daca
actiunea de tip on page este permisa si se poate finaliza cu succes, caz in care
metoda intoarce boolean-ul true, iar false in caz contrar. De asemenea, si aici
avem o metoda universala ca sa faciliteze apelarea unica pentru orice pagina si
se numeste tryToDoOnPageAction.

- Interfata visitor contine declararea tuturor metodelor folosite in ambii visitori.
Clasa Constants din package-ul currentinfo e alcatuita din toate constantele
folosite pe parcursul proiectului (ne ajuta sa evitam erori de tip magic number).

*******************************************************************************



