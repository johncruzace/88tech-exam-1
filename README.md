# 88tech-exam-1

*Currently inserted base currency for get exchange rate (PHP, USD, EUR)*

### API Usages

**GET** /rate-test/rates/ - *Retrieve All latest Rates on Current Date.*

**GET** /rate-test/rates/**{currencyName}** - *Retrieve latest by Currency, currencyName is one of the currently inserted base currency.*

**GET** /rate-test/rates/**{currencyName}**/**{generatedDate}** - *Retrieve latest by Currency and Generated Date. currencyName is one of the currently inserted base currency. generated date is a date filter*


### MultiThread Sample
* Please see MultiThread directory for the implementation of Producer and Consumer.

