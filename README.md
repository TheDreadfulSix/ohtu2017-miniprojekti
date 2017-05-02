# ohtu2017-miniprojekti
[![Build Status](https://travis-ci.org/TheDreadfulSix/ohtu2017-miniprojekti.svg?branch=master)](https://travis-ci.org/TheDreadfulSix/ohtu2017-miniprojekti)
[![Coverage Status](https://coveralls.io/repos/github/TheDreadfulSix/ohtu2017-miniprojekti/badge.svg?branch=master)](https://coveralls.io/github/TheDreadfulSix/ohtu2017-miniprojekti?branch=master)

User Stories, Product Backlog yms.:
https://docs.google.com/spreadsheets/d/1Ae-Kv7YlXyFc_LEV2iS2rGKV_Lyns7GgT9SFqVMKJj4/edit?usp=sharing

Hae koko repo(clooni). -> Luo branchi(git remote ....), Ja tee koodi/muutokset branchiin. Masteriin vain "Base"/pohja projektille/ muut tärkeä (travis etc.) -> Katotaan myöhemmin miten me mergetään, yms. vai vaihdetaanko forkaamiseen.

Hyvä linkki remote/branch komentoihin: https://git-scm.com/book/en/v2/Git-Basics-Working-with-Remotes

UML(karkea versio):https://yuml.me/42d7b4a0 (edit linkki:http://yuml.me/edit/42d7b4a0)

### Käyttöohjeet:

##### Ohjelman avaaminen
Lataa itsellesi tämän repositorion juurikansiosta löytyvä .jar -loppuinen tiedosto ja avaa se. 

##### Viitteen lisääminen
Valitse yläpalkista Reference -> Add, avautuu ikkuna josta voit valita viitteen tyypin "source"-kohdasta.
Kaikki "Required fields" otsikon alta löytyvät kentät tulee täyttää, "Alternative fields" otsikon alta 
(jos kenttiä on kyseiselle viitetyypille) TOINEN kenttä tulee täyttää ja "Optional" otsikon alaiset kentät 
voi täyttää halutessaan. 
Kentän "Citation key" sisällön tulee olla uniikki ja "Year"-, "Volume"- sekä "Number"-kenttien sisältöjen tulee
olla numeromuodossa.
Jos tiedot on täytetty oikein, viite tallentuu painaessasi "Create" -painiketta, muutoin ohjelma ilmoittaa
virhetilanteesta.
Viitteen luonti-ikkunasta voi poistua milloin tahansa painamalla "Close".

##### Viitteiden listaaminen
Kaikki tallentamasi viitteet näkyvät sovelluksen perusnäkymässä listana, josta näkyy viitteen tyyppi (vasemmassa 
yläkulmassa) ja luonnin ohessa täytettyjen kenttien nimet ja arvot. 

##### Viitteen poistaminen
Perusnäkymässä listattujen tallennettujen viitteiden poistaminen onnistuu painamalla "Delete" nappia halutun
viitteen oikealla puolella. Ohjelma varmistaa poiston kysyen "Are you sure?", jos Käyttäjä klikkaa varmistusikkunasta 
"OK", viite poistuu sekä ohjelman listasta, että tietokannasta, Käyttäjän valitessa "Cancel" mitään poistoa ei tapahdu.

##### Viitteen muokkaaminen
--to do--

##### .bib-tiedoston luominen tallennetuista viitteistä
Valitse yläpalkista ".bib" -> "Generate .bib file", avautuu uusi ikkuna, johon "Filename" kenttään tulee syöttää
luotavan tiedoston nimi, jos nimi on jo käytössä, Käyttäjän antamaan nimeen lisätään perään TimeStamp, eli tiedoston
luontiaika (muodossa vuosi-kuukausi-päivä-tunti-minuutti).
Kenttään "Path" tulee syöttää tiedostolle polku(sijainti) johon se tallentuu. Jos tämä kenttä jätetään tyhjäksi, tallentuu
.bib-tiedosto samaan kansioon mistä ohjelma ajettiin(missä .jar-tiedosto sijaitsee).
Tiedosto luodaan ja tallennetaan klikkaamalla "Generate".
.bib-tiedoston luonti-ikkunan voi sulkea milloin tahansa klikkaamalla "Close".

##### Viitteiden haku avainsanalla
Perusnäkymässä on vasemmassa yläkulmassa kenttä "Filter by keyword". Käyttäjän syöttäessä kenttään jonkin sanan ja 
klikkatessa sitten "Filter", ohjelma etsii viitteitä joissa sana esiintyy jossakin viitteen kentässä ja 
muuttaa näkymää siten, että listassa ovat vain nämä viitteet.
Jos avainsanalla ei löydy yhtään viitettä, ohjelma antaa tyhjän näkymän. 
Jos taas Käyttäjä haluaa takaisin kaikkien viitteiden listaan, voi "Filter by keyword"-kentän jättää tyhjäksi ja klikata
"Filter"-painiketta, jolloin kaikki viitteet taas näytetään.
  