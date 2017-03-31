# ohtu2017-miniprojekti
[![Build Status](https://travis-ci.org/TheDreadfulSix/ohtu2017-miniprojekti.svg?branch=master)](https://travis-ci.org/TheDreadfulSix/ohtu2017-miniprojekti)
[![Coverage Status](https://coveralls.io/repos/github/TheDreadfulSix/ohtu2017-miniprojekti/badge.svg?branch=master)](https://coveralls.io/github/TheDreadfulSix/ohtu2017-miniprojekti?branch=master)

User Stories, Product Backlog yms.:
https://docs.google.com/spreadsheets/d/1Ae-Kv7YlXyFc_LEV2iS2rGKV_Lyns7GgT9SFqVMKJj4/edit?usp=sharing

Oma koneeni, sekä Travis-cl ei tunne javafx(antaa error ja ei compilaa javafx:n osalta), eli meidän pitää jotenkin lisätä projektiin a)gradle-javafx plugin, b)javafx:n jfxrt.jar(java run-time librarynä), tai c) depencyt kuntoon gradlen osalta(itse en tätä saanut aikaan, oma java8:ni ei suostunut yhteistyöhön). Toki muitakin mahdollisuuksia luultavasti löytyy, mutta nuo nyt tulivat mieleen.

Hae koko repo(clooni). -> Luo branchi(git remote ....), Ja tee koodi/muutokset branchiin. Masteriin vain "Base"/pohja projektille/ muut tärkeä (travis etc.) -> Katotaan myöhemmin miten me mergetään, yms. vai vaihdetaanko forkaamiseen.
