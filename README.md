# ohtu2017-miniprojekti
[![Build Status](https://travis-ci.org/TheDreadfulSix/ohtu2017-miniprojekti.png?branch=master)](https://travis-ci.org/TheDreadfulSix/ohtu2017-miniprojekti)
[![Coverage Status](https://coveralls.io/repos/github/TheDreadfulSix/ohtu2017-miniprojekti/badge.png?branch=master)](https://coveralls.io/github/TheDreadfulSix/ohtu2017-miniprojekti?branch=master)

User Stories, Product Backlog yms.:
https://docs.google.com/spreadsheets/d/1Ae-Kv7YlXyFc_LEV2iS2rGKV_Lyns7GgT9SFqVMKJj4/edit?usp=sharing

Hyvä linkki remote/branch komentoihin: https://git-scm.com/book/en/v2/Git-Basics-Working-with-Remotes

UML(karkea prototype vaiheen versio):https://yuml.me/42d7b4a0 (edit linkki:http://yuml.me/edit/42d7b4a0)

### Käyttöohjeet:

##### Ohjelman avaaminen
Lataa itsellesi tämän repositorion juurikansiosta löytyvä .jar -loppuinen tiedosto ja avaa se. 

##### Viitteen lisääminen
Valitsemalla yläpalkista "Actions" -> "Add Reference", avautuu ikkuna josta voi valita viitteen tyypin "Source"-kohdasta.
Kaikki "Required fields" otsikon alta löytyvät kentät tulee täyttää, "Alternative fields" otsikon alta 
(jos vaihtoehtoisia kenttiä on kyseiselle viitetyypille) TOINEN kenttä tulee täyttää ja "Optional" otsikon alaiset kentät 
voi täyttää halutessaan. 
Kentän "Citation key" sisällön tulee olla yksilöllinen ja "Year"-, "Volume"- sekä "Number"-kenttien sisältöjen tulee
olla numeromuodossa. 
Jos tiedot on täytetty oikein, viite tallentuu klikkaamalla "Create" -painiketta, muutoin ohjelma ilmoittaa
virhetilanteesta.
Viitteen luonti-ikkunasta voi poistua milloin tahansa painamalla "Close".

##### Viitteiden listaaminen
Kaikki tallennetut viitteet näkyvät sovelluksen perusnäkymässä listana, josta näkyy viitteen tyyppi (vasemmassa 
yläkulmassa) ja luonnin ohessa täytettyjen kenttien nimet ja arvot. 

##### Viitteen poistaminen
Perusnäkymässä listattujen tallennettujen viitteiden poistaminen onnistuu painamalla "Delete" nappia halutun
viitteen oikealla puolella. Ohjelma varmistaa poiston kysyen "Are you sure?", jos Käyttäjä klikkaa varmistusikkunasta 
"OK", viite poistuu sekä ohjelman listasta, että tietokannasta, Käyttäjän valitessa "Cancel" mitään poistoa ei tapahdu.

##### Viitteen muokkaaminen
Näkymässä listattua viitettä voi muokata klikkaamalla listassa olevan viitteen oikeassa laidassa sijaitsevaa "Edit" painiketta. 
Uusi "Edit reference" -ikkuna avautuu ja valittua viitettä voi muokata muuttamalla haluamiensa kenttien sisältöä tai täyttämällä
uusia. Joidenkin kenttien sisältöä ei voi muuttaa ja ne näkyvät harmaina. 
Tietojen syöttämisen jälkeen "Save changes" painiketta painamalla muokkaukset tallentuvat, ellei kenttien täytössä ole puutteita.
"Edit reference" -ikkunan voi sulkea milloin tahansa klikkaamalla "Close". 

##### BibTeX-tiedoston luominen tallennetuista viitteistä
Valitse yläpalkista "Actions" -> "Generate BibTeX file", avautuu uusi ikkuna, johon "Filename" kenttään tulee syöttää
luotavan tiedoston nimi, johon ei tarvitse lisätä .bib -päätettä erikseen. Jos nimi on jo käytössä, Käyttäjän antamaan nimeen 
lisätään perään TimeStamp, eli tiedoston luontiaika (muodossa vuosi-kuukausi-päivä-tunti-minuutti).
Kenttään "Path" tulee syöttää tiedostolle polku(sijainti) johon se tallentuu. Jos tämä kenttä jätetään tyhjäksi, tallentuu
BibTeX-tiedosto samaan kansioon mistä ohjelma ajettiin(missä .jar-tiedosto sijaitsee).
Tiedosto luodaan ja tallennetaan klikkaamalla "Generate".
BibTeX-tiedoston luonti-ikkunan voi sulkea milloin tahansa klikkaamalla "Close".

##### Viitteiden haku avainsanalla
Perusnäkymässä on vasemmassa yläkulmassa kenttä "Filter by keyword". Käyttäjän syöttäessä kenttään jonkin sanan
ja ohjelma näyttää viitteet joissa sana esiintyy jossakin viitteen kentässä.
Jos avainsanalla ei löydy yhtään viitettä, ohjelma antaa tyhjän näkymän. 
Jos taas Käyttäjä haluaa takaisin kaikkien viitteiden listaan, voi "Filter by keyword"-kentän jättää tyhjäksi,
jolloin kaikki viitteet taas näytetään.

##### "Tagien" eli avainsanojen lisääminen viitteeseen
Sekä "Create reference"- , että "Edit reference" -ikkunoissa on kaikkien viitteen attribuuttikenttien jälkeen kenttä nimeltä "Tags"
johon voi syöttää avainsanoja viitteen löytämistä helpottamaan. Avainsanat tulee erottaa kentässä välilyönnillä eivätkä ne saa
sisältää muita merkkejä kuin kirjaimia tai numeroita.
  
