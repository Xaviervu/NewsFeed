# NewsFeed
This project shows news from Lenta.Ru to a RecyclerView
Data is retreived from internet and saved to a local DB from where the data is read by 5 elements usin PagedListAdapter

When an element is selected and the application is closed, a notification shows and if it is tapped, it scrolls the RecyclerView until this post if still exists

This project is written in Kotlin and uses Room + lifeData + ViewModel,
Retrofit2 and Gson.



