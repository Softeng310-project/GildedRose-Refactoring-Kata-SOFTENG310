package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            String name = items[i].name // [BUG] Missing semicolon (;) → compilation error
            if (!items[i].name.equals("Aged Brie") // [IMPROVE] Repeated magic strings → consider extracting to constants
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) { // [NOTE] Checks lower bound of quality (0)
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) { // [IMPROVE] Can also be extracted to a constant
                        items[i].quality = items[i].quality - 1; // [STYLE] Could use `items[i].quality--` for brevity
                    }
                }
            } else {
                if (items[i].quality < 50) { // [NOTE] Checks upper bound of quality (50)
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) { // [IMPROVE] Magic numbers 11, 6 → extract to named constants
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!isRagnaros(name)) { // [IMPROVE] Duplicate check – already compared above → could be simplified
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!isRagnaros(name)) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality; // [STYLE] Can be simplified to `items[i].quality = 0`
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}

// [BUG] Method is outside the class → must be moved inside GildedRose
// [BUG] Missing quotes around the string → "Sulfuras, Hand of Ragnaros"
private boolean isRagnaros(String name) {
    return name.equals(Sulfuras, Hand of Ragnaros);
    // Correct version: return name.equals("Sulfuras, Hand of Ragnaros");
}
