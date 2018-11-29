# comp250a4

## Current problem
The search used in the tester doesn't account for the overwritting of values in the hashMap when the keys are the same, but not the year. Therefore, if two songs have the same title, the second one overwrites the first one in the table, so the year and artist name get overwritten, as intended.
However, the search used in the tested in the tester finds ALL instances of year or artist, without taking account overwritting.
