# kotlin_prosjekter
ulike prosjekter i kotlin
```
prosjektet valutakurser_csv henter data fra csv-filer fra Norges Bank og har følgende funksjonalitet:
- valutakalkulator for utvalgte valutaer: NOK, DKK, EUR, SEK, USD og GBP
- sammenligne to valutaer over er en periode: eks NOK vs EUR
- sammenligne alle valutaene mot hverandre og hvordan de har utviklet seg i forhold til hverandre\
```
```
Converter:
-koverterer mellom ulike enheter for væsker(fluids), vekt og distanse
```
```
KotlinValutaApp:
-Kobler seg til egen server som er skrevet i django
-Henter nyeste verdi om alle valutaer i api-et til server og lagrer dem i sharedpref
-Bruker disse dataene til å oversette mellom valutaene
-Henter bildet på base64 format, lagrer det i sharedPref og viser bildet.
-Bildet er en graf over utviklingen mellom to valutaer gjennom flere mnd
```
