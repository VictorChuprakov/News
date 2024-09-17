package com.example.movies.shared

import com.example.movies.R

enum class NewsCategory (val apiName: String, val displayNameResId: Int) {
        POLITICS("politics", R.string.politics),
        ECONOMICS("economics", R.string.economics),
        BUSINESS("business", R.string.business),
        SOCIETY("society", R.string.society),
        SCIENCE("science", R.string.science),
        TECHNOLOGY("technology", R.string.technology),
        CULTURE("culture", R.string.culture),
        SPORTS("sports", R.string.sports),
        ENTERTAINMENT("entertainment", R.string.entertainment),
        HEALTH("health", R.string.health),
        NATURAL_EVENTS("natural_events", R.string.natural_events),
        NATURE("nature", R.string.nature),
        INCIDENTS("incidents", R.string.incidents),
        EDUCATION("education", R.string.education),
        CRIME("crime", R.string.crime),
        RELIGION("religion", R.string.religion),
        INTERNATIONAL_RELATIONS("international_relations", R.string.international_relations);
}