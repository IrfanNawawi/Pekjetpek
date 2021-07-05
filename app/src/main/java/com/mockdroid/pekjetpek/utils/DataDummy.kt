package com.mockdroid.pekjetpek.utils

import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity


object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                1,
                508943,
                "Luca",
                "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.",
                "2021-06-17",
                "/jTswp6KyDYKtvC52GbHagrZbGvD.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                2,
                385128,
                "F9",
                "Dominic Toretto and his crew battle the most skilled assassin and high-performance driver they've ever encountered: his forsaken brother.",
                "2021-05-19",
                "/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                3,
                337404,
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                4,
                423108,
                "The Conjuring: The Devil Made Me Do It",
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
                "2021-05-25",
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                5,
                637649,
                "Wrath of Man",
                "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                "2021-04-22",
                "/M7SUK85sKjaStg4TKhlAVyGlz3.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                6,
                503736,
                "Army of the Dead",
                "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                "2021-05-14",
                "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                7,
                337404,
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                8,
                423108,
                "The Conjuring: The Devil Made Me Do It",
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
                "2021-05-25",
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                9,
                637649,
                "Wrath of Man",
                "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
                "2021-04-22",
                "/M7SUK85sKjaStg4TKhlAVyGlz3.jpg",
                7.1,
                false
            )
        )
        movies.add(
            MovieEntity(
                10,
                503736,
                "Army of the Dead",
                "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
                "2021-05-14",
                "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
                7.1,
                false
            )
        )
        return movies
    }

    fun getDummyDetailMovie(): MovieEntity {
        return MovieEntity(
            10,
            503736,
            "Army of the Dead",
            "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
            "2021-05-14",
            "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
            7.1,
            false
        )
    }

    fun generateDummyTvShow(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                1,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                2,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                3,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                4,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                5,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                6,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                7,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                8,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                9,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        tvShows.add(
            TvShowEntity(
                10,
                84958,
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                7.1,
                false
            )
        )
        return tvShows
    }

    fun getDummyDetailTvShow(): TvShowEntity {
        return TvShowEntity(
            10,
            84958,
            "Loki",
            "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
            "2021-06-09",
            "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
            7.1,
            false
        )
    }
}