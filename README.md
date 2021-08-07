# kotlin_prosjekter
ulike prosjekter i kotlin
```
prosjektet valutakurser_csv henter data fra csv-filer fra Norges Bank og har følgende funksjonalitet:
- valutakalkulator for utvalgte valutaer: NOK, DKK, EUR, SEK, USD og GBP
- sammenligne to valutaer over er en periode: eks NOK vs EUR
- sammenligne alle valutaene mot hverandre og hvordan de har utviklet seg i forhold til hverandre
```
```
Converter:
- koverterer mellom ulike enheter for væsker(fluids), vekt og distanse
```
```
KotlinValutaApp:
- Kobler seg til egen server som er skrevet i django
- Henter nyeste verdi om alle valutaer i api-et til server og lagrer dem i sharedpref
- Bruker disse dataene til å oversette mellom valutaene
- Henter bildet på base64 format, lagrer det i sharedPref og viser bildet.
  -Bildet er en graf over utviklingen mellom to valutaer gjennom flere mnd
```
```
valuta2:
en utvidelse av KotlinValutaApp, men denne bruker fragments fremfor activities
note: er en bug når man roterer mobil/emulator som får appen til å avslutte
har følgende funksjonalitet:
- Kobler seg til egen server som er skrevet i django
- Henter nyeste verdi om alle valutaer i api-et til server og lagrer dem i sharedpref
- Bruker disse dataene til å oversette mellom valutaene
- Henter bildet på base64 format, lagrer det i sharedPref og viser bildet.
- Bildet er en graf over utviklingen mellom to valutaer gjennom flere mnd
- Henter et ekstra bilde kontra det KotlinValutaApp gjør. Bildet er blir hentet
lagret på base64-format
  - bildet viser flere valutaer sin utvikling mot en "base" valuta gjennom flere mnd
```
```
dexter:
En pokedex som lar bruker søke etter pokemon basert på enten navn eller nummer
har følgende funksjonalitet:
- Kobler seg til egen server som er skrevet i django
- Henter info om pokemon og lagrer det i sharedPref, gir evt feilmelding om navn/nummer er feil
- Bruker disse dataene til å vise info om den aktuelle pokemonen
- Henter bildet på base64 format, lagrer det i sharedPref og viser bildet.
- Bildet er av en pokemon
- NB: har ikke funktionalitet for å rotere mobil/emulator
- Nb nr2: refresh-knappen er der pga bildet og informasjonen hentes async
```
