Android Design Utils
===================================

A simple library that uses new Android Design APIs (see: http://android-developers.blogspot.it/2015/05/android-design-support-library.html)

This library is an heavy WIP so expect updates and tewaks to existing classes.

Classes:
-------
- BaseCollapsingContainerActivity -> Activity with CollapsingToolbar where you can provide a custom fragment as content
- BaseCollapsingScrollViewActivity -> Activity with NestedScrollView where you can inflate your custom views
- BaseCoordinatorContainerActivity -> Activity with scrolling toolbar and where you have to inflate your custom fragment
- BaseRecyclerCoordinatorActivity -> Activity with scrolling toolbar that uses RecyclerView, you have to pass you custom RecyclerView Adapter

How To:
-------
You class must extend one of the classes listed above and implement all the abstract methods.


