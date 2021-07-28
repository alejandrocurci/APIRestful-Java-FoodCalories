# API Restful - Java - Food calories

**Spring Framework - Persistance in json file**

This simple API loads some food ingredients from a json file and allows consumers to look for them and make dishes. 

The following endpoints are available:

(GET)    /ingredients                         -> fetches all ingredients available

Order filters can be included ("asc"/"desc"). By default, no specific order is applied.

(GET)    /ingredients?order=asc                         -> fetches all ingredients available in ascendent order

(GET)    /ingredients?order=desc                         -> fetches all ingredients available in descendent order

(POST)    /calories         -> calculates the calories for the resulting dish.

It requires a body object like the following example:

{
    "dish": "Salad",
    "ingredients": [
        {
            "ingredient": "Apio",
            "weight": 1000
        },
        {
            "ingredient": "Berenjena",
            "weight": 100
        }
    ]
}

(POST)    /dishes      -> calculates the calories for each dish in the body list.

It requires a body object like the following example:

{
    "dishes": [
        {
            "dish": "Dish 1",
            "ingredients": [
                {
                    "ingredient": "Apio",
                    "weight": 500
                },
                {
                    "ingredient": "Berenjena",
                    "weight": 10
                }
            ]
        },
        {
            "dish": "Dish 2",
            "ingredients": [
                {
                    "ingredient": "Apio",
                    "weight": 1000
                },
                {
                    "ingredient": "Berenjena",
                    "weight": 100
                }
            ]
        },
        {
            "dish": "Dish 3",
            "ingredients": [
                {
                    "ingredient": "Apio",
                    "weight": 300
                },
                {
                    "ingredient": "Berenjena",
                    "weight": 200
                }
            ]
        }
    ]
}


