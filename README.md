# App's Overview

* In this article I am gonna take you through the development of 
  Swipe Delete Feature, specifically we are going to delete the 
  data present in Cloud Firestore Database in just a swipe.

* Sounds amazing, yes it is amazing, let's start :

{% youtube https://www.youtube.com/watch?v=sG1O5zXQJQI&ab_channel=SiddharthSinghBaghel %}
<br/>

### <u>Note</u>: 

<p>The article only contains the explanation of "swipe to delete" feature, firebase implementation and UI details are not discussed, but I will provide the GitHub from which you can easily go through the complete code of the app.<p/>

## Table Of Content

- [Dependencies Setup ](#setup)
- [Code Explanation](#code)
- [Full code - GitHub Repo ](#GitHub)
- [Writer's Support ❤️](#support)

<a name="setup"></a>
##Dependencies Setup

* I have used an external library to create and decorate the swipe to delete feature, it is called [RecyclerViewSwipeDecorator](https://github.com/xabaras/RecyclerViewSwipeDecorator).

```
dependencies{

   implementation 'it.xabaras.android:recyclerview-swipedecorator:1.2.3'

}

```
<a name="code"></a> 
## Code Explanation
<p>

* Create an `ItemTouchHelper.SimpleCallback`, instantiate an  
  `ItemTouchHelper` with this callback.
* Copy the code snippet below 👇

> Reference - [ListActivity.kt](https://github.com/Siddharth-sing/Swipe-delete-from-firebase-/blob/a253f5ea2286ff2ed7c97edacb3046fae18b4ba1/app/src/main/java/com/siddharthsinghbaghel/swipedeletefirebase/ListActivity.kt#L54)

```
/*

   -> `SimpleCallback` gives us option to tell about the swipe 
       direction.

   -> `ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT`

*/

   val callback: ItemTouchHelper.SimpleCallback = object :

            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

            override fun onMove(

                recyclerView: RecyclerView,

                viewHolder: RecyclerView.ViewHolder,

                target: RecyclerView.ViewHolder

            ): Boolean {

                return false

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

              
               //Take action for swiped direction 

               //Delete on swipe left

               //Archive on swipe right

              //Customise according to your mood

         }

```

* Now let us attach our `ItemTouchHelper` with the `RecyclerView`.

* Copy the below 👇 code snippet just outside `callback`

```
val itemTouchHelper = ItemTouchHelper(callback)

        itemTouchHelper.attachToRecyclerView(rv)

/* rv - replace with your  recycler view variable name */

```

* The above code is sufficient for the swipe to delete feature. It will look something like below 👇 

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/ah9yjj7r297kopkq6qwa.gif)

* To decorate our swipe feature we will override `onChildDraw` method. This method has various functions, some of them will be discussed in this article others you can explore [here](https://github.com/xabaras/RecyclerViewSwipeDecorator#customizing).

* Override `onChildDraw` method below `onSwiped` method.

* Copy the below code snippet👇

```
  override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {



                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                    .addBackgroundColor(

                        ContextCompat.getColor(

                            this@ListActivity,

                            android.R.color.holo_red_light

                        )

                    )

                    .addActionIcon(R.drawable.ic_baseline_delete_sweep_24)  // add any icon of your choice

                    .addSwipeRightLabel("Deleting the Item") //Label according to your choice

                    .addSwipeLeftLabel("Deleting the Item")

                    .setSwipeRightLabelColor(R.color.white) // behind color on  swiping

                    .setSwipeLeftLabelColor(R.color.white)
                    .create()
                    .decorate()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            }



```
* That's all now you can see the magic 😇

<p/>
<a name="GitHub"></a> 
## GitHub Repository
* Fork the repository for better and easy understanding and for quick learning.
{% github https://github.com/Siddharth-sing/Swipe-delete-from-firebase- %}

<a name="support"></a> 
## Writer's Support

* If you find the article useful show some ❤️ by staring at some of my repositories and following me on dev.to and github.
 <div>
  <p align="middle">
  <a href="https://www.linkedin.com/in/siddharth-singh-baghel-912866190/">
  <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white">
  </a>
  <a href="https://github.com/Siddharth-sing">
  <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white">
  </a>
  <a href="https://dev.to/siddharthsing">
  <img src="https://img.shields.io/badge/dev.to-0A0A0A?style=for-the-badge&logo=dev.to&logoColor=white">
  </a>
