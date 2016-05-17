package pl.wasat.smarthma.helper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.Collection;
import pl.wasat.smarthma.model.CollectionsGroup;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.mission.MissionItemData;
import pl.wasat.smarthma.utils.time.SimpleDate;

/**
 *  Used for sorting collections containing metadata downloaded from ESA servers.
 */
public class DataSorter {

    /**
     * Sorts lists and collections. In case of ISO and OM entries, the given list will be sorted
     * using the most recently picked sorting type specified in the SmartHMApplication class.
     *
     * @param list  a collection of objects compatible with this class
     */
    public void sort(List list) {
        if (!list.isEmpty()) {
            Object o = list.get(0);
            if (o instanceof Collection) {
                sortCollections(list);
            } else if (o instanceof CollectionsGroup) {
                sortCollectionsGroups(list);
            } else if (o instanceof MissionItemData) {
                sortMissionItemsData(list);
            } else if (o instanceof Entry) {
                sortOMEntries(list);
            } else if (o instanceof EntryISO) {
                sortISOEntries(list);
            }
        }
    }

    /**
     * Used by most methods in this class.
     * @param list          a collection of entries
     * @param comparator    the comparator describing how to sort given list
     */
    private void sortCollection(List list, Comparator comparator) {
        if (list == null || comparator == null) {
            return;
        }
        try {
            Collections.sort(list, comparator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts lists of SmartHMA Collection objects.
     * @param list  a list of collections
     */
    private void sortCollections(List<Collection> list) {
        Comparator comparator = new Comparator<Collection>() {
            public int compare(Collection c1, Collection c2) {
                String str1 = c1.getName().trim();
                String str2 = c2.getName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA CollectionsGroup objects.
     * @param list  a list of groups
     */
    private void sortCollectionsGroups(List<CollectionsGroup> list) {
        Comparator comparator = new Comparator<CollectionsGroup>() {
            public int compare(CollectionsGroup c1, CollectionsGroup c2) {
                String str1 = c1.getGroupName().trim();
                String str2 = c2.getGroupName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA MissionItemData objects.
     * @param list  a list of items
     */
    private void sortMissionItemsData(List<MissionItemData> list) {
        Comparator comparator = new Comparator<MissionItemData>() {
            public int compare(MissionItemData c1, MissionItemData c2) {
                String str1 = c1.getName().trim();
                String str2 = c2.getName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA Entry objects.
     * @param entries  a list of entries
     */
    private void sortOMEntries(List<Entry> entries) {
        if (!entries.isEmpty()) {
            if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_ASCENDING) {
                sortOMEntriesByTitleAscending(entries);
            } else if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_DESCENDING) {
                sortOMEntriesByTitleDescending(entries);
            } else if (SmartHMApplication.sortingType == Const.SORT_BY_DATE_ASCENDING) {
                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0) {
                    sortOMEntriesByDatePublishedAscending(entries);
                } else {
                    sortOMEntriesByDateUpdatedAscending(entries);
                }
            } else {
                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0) {
                    sortOMEntriesByDatePublishedDescending(entries);
                } else {
                    sortOMEntriesByDateUpdatedDescending(entries);
                }
            }
        }
    }

    /**
     * Sorts lists of SmartHMA Entry objects by their title in ascending order.
     * @param list  a list of entries
     */
    private void sortOMEntriesByTitleAscending(List<Entry> list) {
        Comparator comparator = new Comparator<Entry>() {
            public int compare(Entry c1, Entry c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA Entry objects by their title in descending order.
     * @param list  a list of entries
     */
    private void sortOMEntriesByTitleDescending(List<Entry> list) {
        Comparator comparator = new Comparator<Entry>() {
            public int compare(Entry c1, Entry c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str2.compareTo(str1);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA Entry objects by their date in ascending order.
     * @param list  a list of entries
     */
    private void sortOMEntriesByDatePublishedAscending(List<Entry> list) {
        Comparator comparator = new Comparator<Entry>() {
            public int compare(Entry c1, Entry c2) {
                SimpleDate v1 = new SimpleDate(c1.getPublished());
                SimpleDate v2 = new SimpleDate(c2.getPublished());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA Entry objects by their date in descending order.
     * @param list  a list of entries
     */
    private void sortOMEntriesByDatePublishedDescending(List<Entry> list) {
        Comparator comparator = new Comparator<Entry>() {
            public int compare(Entry c1, Entry c2) {
                SimpleDate v1 = new SimpleDate(c1.getPublished());
                SimpleDate v2 = new SimpleDate(c2.getPublished());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA Entry objects by their update time in ascending order.
     * @param list  a list of entries
     */
    private void sortOMEntriesByDateUpdatedAscending(List<Entry> list) {
        Comparator comparator = new Comparator<Entry>() {
            public int compare(Entry c1, Entry c2) {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA Entry objects by their update time in descending order.
     * @param list  a list of entries
     */
    private void sortOMEntriesByDateUpdatedDescending(List<Entry> list) {
        Comparator comparator = new Comparator<Entry>() {
            public int compare(Entry c1, Entry c2) {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA EntryISO objects.
     * @param entries  a list of entries
     */
    private void sortISOEntries(List<EntryISO> entries) {
        if (!entries.isEmpty()) {
            if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_ASCENDING) {
                sortISOEntriesByTitleAscending(entries);
            } else if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_DESCENDING) {
                sortISOEntriesByTitleDescending(entries);
            } else if (SmartHMApplication.sortingType == Const.SORT_BY_DATE_ASCENDING) {
                //sortISOEntriesByDateAscending(entries);
                sortISOEntriesByDateUpdatedAscending(entries);
            } else {
                //sortISOEntriesByDateDescending(entries);
                sortISOEntriesByDateUpdatedDescending(entries);
            }
        }
    }

    /**
     * Sorts lists of SmartHMA Entry objects by their title in ascending order.
     * @param list  a list of entries
     */
    private void sortISOEntriesByTitleAscending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA EntryISO objects by their title in descending order.
     * @param list  a list of entries
     */
    private void sortISOEntriesByTitleDescending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str2.compareTo(str1);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA EntryISO objects by their date in ascending order.
     * @param list  a list of entries
     */
    void sortISOEntriesByDateAscending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                SimpleDate v1 = new SimpleDate(c1.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                SimpleDate v2 = new SimpleDate(c2.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA EntryISO objects by their date in descending order.
     * @param list  a list of entries
     */
    void sortISOEntriesByDateDescending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                SimpleDate v1 = new SimpleDate(c1.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                SimpleDate v2 = new SimpleDate(c2.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA EntryISO objects by their update time in ascending order.
     * @param list  a list of entries
     */
    private void sortISOEntriesByDateUpdatedAscending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    /**
     * Sorts lists of SmartHMA EntryISO objects by their update time in descending order.
     * @param list  a list of entries
     */
    private void sortISOEntriesByDateUpdatedDescending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }
}
