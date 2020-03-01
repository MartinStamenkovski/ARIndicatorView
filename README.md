# ARIndicatorView

ARIndicatorView is Android library for showing indicators in RecyclerView, ViewPager or in something else you build custom.

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/guide/)
[![sad](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?url=https://github.com/MartinStamenkovski/ARIndicatorView&text=ARIndicatorView%20Android&hashtags=Android,Indicators,RecyclerView)

<a href="https://www.buymeacoffee.com/QE59zvs" target="_blank"><img src="https://bmc-cdn.nyc3.digitaloceanspaces.com/BMC-button-images/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>

## Installation
[![](https://jitpack.io/v/MartinStamenkovski/ARIndicatorView.svg)](https://jitpack.io/#MartinStamenkovski/ARIndicatorView)


1. Add JitPack to your project build.gradle
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
   }
}
```
2. Add the dependency in the application build.gradle

*For those who are not using androidx.*
```gradle
dependencies {
    implementation 'com.github.martinstamenkovski:ARIndicatorView:1.0.0'
  }
```
*Androidx*
```gradle
dependencies {
    implementation 'com.github.martinstamenkovski:ARIndicatorView:1.1.0'
  }
```
## Usage  
**XML**
```xml
<com.arindicatorview.ARIndicatorView
            android:id="@+id/ar_indicator"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:gravity="center"
            android:orientation="horizontal"
            app:indicator_size="15"
            app:number_of_indicators="5"
            app:indicator_color="@color/colorAccent"
            app:selected_color="@color/colorPrimary"
            app:indicator_animation="@anim/zoom_in"
            app:indicator_shape="@drawable/round_shape"
            app:indicator_scrubbing="true" 
            app:animate_indicator_scrubbing="true"
    />
```
 
## Kotlin or Java

**You need to attach the ARIndicatorView to RecyclerView or ViewPager after populating the adapter, else the indicators will not create.**
 
**Example**
```java
recyclerView.adapter = Adapter(this, data)
recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
arIndicatorView.attachTo(recyclerView, true)
```
- The parameter **_true_** indicates that RecyclerView will be paging, by default this is false.  

If you don't want to attach it to RecyclerView or ViewPager you can use the method **setNumberOfIndicators(numberOfIndicators)** later in code.

```java
arIndicatorView.numberOfIndicators = 5
```

## Customization and method usage  
To start with **_number_of_indicators_**, in xml this method will only have effect while you are in preview to design the indicators to your liking, you can use it in your code for the indicators to appear.
- Indicators width and height, default is 10
```java
app:indicator_size="15"
```
- Color to use when indicator is selected, default is BLACK.
```java
app:selected_color="@color/colorPrimary" 
```
- Color to use when indicator is not selected, default is LTGRAY.
```java
app:indicator_color="@color/colorAccent"
```
- Indicator animation when is selected, by default no animation is provided.
```java
app:indicator_animation="@anim/zoom_in"
```
- Indicator shape you can use your own custom shape, or use the default one. Default is circle.

```java
app:indicator_shape="@drawable/circle"
```
#### Example with different shape  
*Note: this will not be the animation when you are changing from one shape to another.*  
<p>  
<img src="https://raw.githubusercontent.com/MartinStamenkovski/ARIndicatorView/gifs/change_shape_gif.gif" alt="" width="220" height="350">   
</p>

- You can also use scrubbing on the indicators for faster scrolling through pages, default value is false.

```java
app:indicator_scrubbing="true" 
```
When **indicator_scrubbing** is set to true you can also specify should the indicator animate when scrubbing.  
```java
app:animate_indicator_scrubbing="true" //Default value is false
```  
#### Example when scrubbing animation is on
<p>  
<img src="https://raw.githubusercontent.com/MartinStamenkovski/ARIndicatorView/gifs/scrubbing.gif" alt="" width="220" height="350">   
</p>

- Default orientation of the indicators is horizontal, but they can be placed vertical too.
```xml
android:orientation="vertical"
```
#### You can use all these methods in code too.

```java
arIndicatorView.indicatorSize = 50
arIndicatorView.indicatorShape = R.drawable.my_shape
arIndicatorView.selectionColor = ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
arIndicatorView.indicatorColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
arIndicatorView.isScrubbingEnabled = true
arIndicatorView.isShouldAnimateOnScrubbing = true
arIndicatorView.indicatorAnimation = R.anim.fade_in
```
++ some extra methods:
```java
setSelectedPosition(int position) //Selects the indicator at the given position
removeIndicators() //Removes all indicators
```

### Todos
- [ ] scrollToNext() -> Method to scroll to next item
- [ ] scrollToPrevios() -> Method to scroll to previous item
- [ ] Support custom shape from custom view

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.


## License
[MIT](https://choosealicense.com/licenses/mit/)
