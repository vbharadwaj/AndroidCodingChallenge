# The Challenge:

The challenge is to create a simple Android app that exercises a REST-ful API. The API endpoint `http://api.nytimes.com/svc/movies/v2/reviews/dvd-picks.json?order=by-date&api-key=b75da00e12d54774a2d362adddcc9bef` returns a JSON object which is a list of different movie reviews published by the New York Times. For example:
```json
  "results": [
    {
      "display_title": "Rat Film",
      "mpaa_rating": "",
      "headline": "Review: In \u2018Rat Film,\u2019 a City of Rodents and Racial Oppression",
        ....

    },
    {
      "display_title": "Woodpeckers",
      "mpaa_rating": "",
      "headline": "Review: \u2018Woodpeckers,\u2019 a Tale of Love and Agonizing Penal Confinement",
      ....
    },
    ..
    ]
```

Using this endpoint, show a list of these items, with each row displaying at least the following fields:
- `display_title` (movie title)
- `mpaa_rating` (movie rating)
- `byline` (reviewer)
- `headline` 
- `summary_short`
- `publication_date`
- `multimedia` 

Feel free to make any assumptions you want along the way or use any third party libraries as needed (just document them in a your pull request).

We're looking for the following in no particular order:

* A bug-free app that displays the list
* Architectural clarity, architecture that can scale
* Clear explanations for any architectural decision you've taken

Throw a little something extra in there to make it your own and get our attention!
