# Back Base Cities
This project has some complication, dude the size of the file to read, this take some time to load, also, we have the capability to seek a register and we can't use a DB so that make a little difficult the problem of seek.

## Solution
I think my solution is no so efficent because I use an ArrayList to perform the search an that kind of collection is not optimaced to do these type of search, I guess a better solution is use a HashMap or a Map to do the search, but we have to transform the result to load on the RecyclerView so I'm not sure if that kind of transform take more time than the search.

Also I changed the behaviour of the seeker, because in the first version I couldn't perform easily a test, so this is the final result:

```java
    public ArrayList<CityInfo> seekOnList(String filter, ArrayList<CityInfo> cityList) {
        ArrayList<CityInfo> itemList = new ArrayList<>();
        for (CityInfo city : cityList) {
            if (city.getName().toLowerCase().startsWith(filter.toLowerCase())) {
                itemList.add(city);
            }
        }
        return itemList;
    }
```