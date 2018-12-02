# comp250a4

## Search by artist/year problem
The search used in the tester doesn't account for the overwritting of values in the hashMap when the keys are the same, but not the year. Therefore, if two songs have the same title, the second one overwrites the first one in the table, so the year and artist name get overwritten, as intended.
However, the search used in the tested in the tester finds ALL instances of year or artist, without taking account overwritting.
### Possible solution
I should be storing the songs with years and artists as keys, but not sure how to do this and still make it work with titles.

## values() problem
I'm not sure if they're unique, needs to be tested. Refer to this https://mycourses2.mcgill.ca/d2l/le/358973/discussions/threads/604473/View
