package pl.wasat.smarthma.helper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.wasat.smarthma.model.Collection;
import pl.wasat.smarthma.model.CollectionsGroup;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.mission.MissionItemData;
import pl.wasat.smarthma.model.om.EntryOM;

/*
 *  Used for sorting collections containing various data.
 */

public class DataSorter {
    public void sort(List list) {
        if (!list.isEmpty()) {
            Object o = list.get(0);
            if (o instanceof Collection) {
                sortCollections(list);
            } else if (o instanceof CollectionsGroup) {
                sortCollectionsGroups(list);
            } else if (o instanceof MissionItemData) {
                sortMissionItemsData(list);
            } else if (o instanceof EntryOM) {
                sortEOEntries(list);
            } else if (o instanceof EntryISO) {
                sortISOEntries(list);
            }
        }
    }

    void sortCollection(List list, Comparator comparator) {
        Collections.sort(list, comparator);
    }

    void sortCollections(List<Collection> list) {
        Comparator comparator = new Comparator<Collection>() {
            public int compare(Collection c1, Collection c2) {
                String str1 = c1.getName().trim();
                String str2 = c2.getName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortCollectionsGroups(List<CollectionsGroup> list) {
        Comparator comparator = new Comparator<CollectionsGroup>() {
            public int compare(CollectionsGroup c1, CollectionsGroup c2) {
                String str1 = c1.getGroupName().trim();
                String str2 = c2.getGroupName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortMissionItemsData(List<MissionItemData> list) {
        Comparator comparator = new Comparator<MissionItemData>() {
            public int compare(MissionItemData c1, MissionItemData c2) {
                String str1 = c1.getName().trim();
                String str2 = c2.getName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }


    void sortEOEntries(List<EntryOM> entries) {
        if (!entries.isEmpty()) {
            if (Global.sortingType == Const.SORT_BY_TITLE_ASCENDING) {
                sortEntriesByTitleAscending(entries);
            } else if (Global.sortingType == Const.SORT_BY_TITLE_DESCENDING) {
                sortEntriesByTitleDescending(entries);
            } else if (Global.sortingType == Const.SORT_BY_DATE_ASCENDING) {
                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0) {
                    sortEntriesByDatePublishedAscending(entries);
                } else {
                    sortEntriesByDateUpdatedAscending(entries);
                }
            } else {
                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0) {
                    sortEntriesByDatePublishedDescending(entries);
                } else {
                    sortEntriesByDateUpdatedDescending(entries);
                }
            }
        }
    }

    void sortISOEntries(List<EntryISO> entries) {
        if (!entries.isEmpty()) {
            if (Global.sortingType == Const.SORT_BY_TITLE_ASCENDING) {
                //sortEntriesByTitleAscending(entries);
            } else if (Global.sortingType == Const.SORT_BY_TITLE_DESCENDING) {
                // sortEntriesByTitleDescending(entries);
            } else if (Global.sortingType == Const.SORT_BY_DATE_ASCENDING) {
/*                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0)
                {
                    sortEntriesByDatePublishedAscending(entries);
                }
                else
                {
                    sortEntriesByDateUpdatedAscending(entries);
                }*/
            } else {
/*                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0)
                {
                    sortEntriesByDatePublishedDescending(entries);
                }
                else
                {
                    sortEntriesByDateUpdatedDescending(entries);
                }*/
            }
        }
    }


    void sortEntriesByTitleAscending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortEntriesByTitleDescending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str2.compareTo(str1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortEntriesByDatePublishedAscending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
                SimpleDate v1 = new SimpleDate(c1.getPublished());
                SimpleDate v2 = new SimpleDate(c2.getPublished());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortEntriesByDatePublishedDescending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
                SimpleDate v1 = new SimpleDate(c1.getPublished());
                SimpleDate v2 = new SimpleDate(c2.getPublished());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortEntriesByDateUpdatedAscending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortEntriesByDateUpdatedDescending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }
}
