package pl.wasat.smarthma.helper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.Collection;
import pl.wasat.smarthma.model.CollectionsGroup;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.mission.MissionItemData;
import pl.wasat.smarthma.model.om.EntryOM;
import pl.wasat.smarthma.utils.time.SimpleDate;

/*
 *  Used for sorting collections containing various data.
 */

public class DataSorter
{
    public void sort(List list)
    {
        if (!list.isEmpty())
        {
            Object o = list.get(0);
            if (o instanceof Collection)
            {
                sortCollections(list);
            }
            else if (o instanceof CollectionsGroup)
            {
                sortCollectionsGroups(list);
            }
            else if (o instanceof MissionItemData)
            {
                sortMissionItemsData(list);
<<<<<<< HEAD
<<<<<<< HEAD
            } else if (o instanceof EntryOM) {
                sortOMEntries(list);
            } else if (o instanceof EntryISO) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
            }
            else if (o instanceof EntryOM)
            {
                sortOMEntries(list);
            }
            else if (o instanceof EntryISO)
            {
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                sortISOEntries(list);
            }
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
    void sortCollection(List list, Comparator comparator) {
        if (list == null || comparator == null) {
            return;
        }
        try {
            Collections.sort(list, comparator);
        } catch (Exception e) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortCollection(List list, Comparator comparator)
    {
        if (list == null || comparator == null)
        {
            return;
        }
        try
        {
            Collections.sort(list, comparator);
        }
        catch (Exception e)
        {
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
            e.printStackTrace();
        }
    }

    void sortCollections(List<Collection> list)
    {
        Comparator comparator = new Comparator<Collection>()
        {
            public int compare(Collection c1, Collection c2)
            {
                String str1 = c1.getName().trim();
                String str2 = c2.getName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortCollectionsGroups(List<CollectionsGroup> list)
    {
        Comparator comparator = new Comparator<CollectionsGroup>()
        {
            public int compare(CollectionsGroup c1, CollectionsGroup c2)
            {
                String str1 = c1.getGroupName().trim();
                String str2 = c2.getGroupName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortMissionItemsData(List<MissionItemData> list)
    {
        Comparator comparator = new Comparator<MissionItemData>()
        {
            public int compare(MissionItemData c1, MissionItemData c2)
            {
                String str1 = c1.getName().trim();
                String str2 = c2.getName().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }


<<<<<<< HEAD
<<<<<<< HEAD
    void sortOMEntries(List<EntryOM> entries) {
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
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortOMEntries(List<EntryOM> entries)
    {
        if (!entries.isEmpty())
        {
            if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_ASCENDING)
            {
                sortOMEntriesByTitleAscending(entries);
            }
            else if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_DESCENDING)
            {
                sortOMEntriesByTitleDescending(entries);
            }
            else if (SmartHMApplication.sortingType == Const.SORT_BY_DATE_ASCENDING)
            {
                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0)
                {
                    sortOMEntriesByDatePublishedAscending(entries);
                }
                else
                {
                    sortOMEntriesByDateUpdatedAscending(entries);
                }
            }
            else
            {
                if (entries.get(0).getPublished().compareTo(Const.DATE_NULL) != 0)
                {
                    sortOMEntriesByDatePublishedDescending(entries);
                }
                else
                {
                    sortOMEntriesByDateUpdatedDescending(entries);
                }
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
            }
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
    void sortOMEntriesByTitleAscending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortOMEntriesByTitleAscending(List<EntryOM> list)
    {
        Comparator comparator = new Comparator<EntryOM>()
        {
            public int compare(EntryOM c1, EntryOM c2)
            {
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    void sortOMEntriesByTitleDescending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortOMEntriesByTitleDescending(List<EntryOM> list)
    {
        Comparator comparator = new Comparator<EntryOM>()
        {
            public int compare(EntryOM c1, EntryOM c2)
            {
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str2.compareTo(str1);
            }
        };
        sortCollection(list, comparator);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    void sortOMEntriesByDatePublishedAscending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortOMEntriesByDatePublishedAscending(List<EntryOM> list)
    {
        Comparator comparator = new Comparator<EntryOM>()
        {
            public int compare(EntryOM c1, EntryOM c2)
            {
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                SimpleDate v1 = new SimpleDate(c1.getPublished());
                SimpleDate v2 = new SimpleDate(c2.getPublished());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    void sortOMEntriesByDatePublishedDescending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortOMEntriesByDatePublishedDescending(List<EntryOM> list)
    {
        Comparator comparator = new Comparator<EntryOM>()
        {
            public int compare(EntryOM c1, EntryOM c2)
            {
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                SimpleDate v1 = new SimpleDate(c1.getPublished());
                SimpleDate v2 = new SimpleDate(c2.getPublished());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    void sortOMEntriesByDateUpdatedAscending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortOMEntriesByDateUpdatedAscending(List<EntryOM> list)
    {
        Comparator comparator = new Comparator<EntryOM>()
        {
            public int compare(EntryOM c1, EntryOM c2)
            {
<<<<<<< HEAD
=======
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortOMEntriesByDateUpdatedDescending(List<EntryOM> list)
    {
        Comparator comparator = new Comparator<EntryOM>()
        {
            public int compare(EntryOM c1, EntryOM c2)
            {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntries(List<EntryISO> entries)
    {
        if (!entries.isEmpty())
        {
            if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_ASCENDING)
            {
                sortISOEntriesByTitleAscending(entries);
            }
            else if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_DESCENDING)
            {
                sortISOEntriesByTitleDescending(entries);
            }
            else if (SmartHMApplication.sortingType == Const.SORT_BY_DATE_ASCENDING)
            {
                //sortISOEntriesByDateAscending(entries);
                sortISOEntriesByDateUpdatedAscending(entries);
            }
            else
            {
                //sortISOEntriesByDateDescending(entries);
                sortISOEntriesByDateUpdatedDescending(entries);
            }
        }
    }

    void sortISOEntriesByTitleAscending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByTitleDescending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str2.compareTo(str1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByDateAscending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                SimpleDate v1 = new SimpleDate(c1.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                SimpleDate v2 = new SimpleDate(c2.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByDateDescending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                SimpleDate v1 = new SimpleDate(c1.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                SimpleDate v2 = new SimpleDate(c2.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByDateUpdatedAscending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

<<<<<<< HEAD
    void sortOMEntriesByDateUpdatedDescending(List<EntryOM> list)
    {
        Comparator comparator = new Comparator<EntryOM>()
        {
            public int compare(EntryOM c1, EntryOM c2)
            {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntries(List<EntryISO> entries)
    {
        if (!entries.isEmpty())
        {
            if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_ASCENDING)
            {
                sortISOEntriesByTitleAscending(entries);
            }
            else if (SmartHMApplication.sortingType == Const.SORT_BY_TITLE_DESCENDING)
            {
                sortISOEntriesByTitleDescending(entries);
            }
            else if (SmartHMApplication.sortingType == Const.SORT_BY_DATE_ASCENDING)
            {
                //sortISOEntriesByDateAscending(entries);
                sortISOEntriesByDateUpdatedAscending(entries);
            }
            else
            {
                //sortISOEntriesByDateDescending(entries);
                sortISOEntriesByDateUpdatedDescending(entries);
            }
        }
    }

    void sortISOEntriesByTitleAscending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByTitleDescending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str2.compareTo(str1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByDateAscending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                SimpleDate v1 = new SimpleDate(c1.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                SimpleDate v2 = new SimpleDate(c2.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByDateDescending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
                SimpleDate v1 = new SimpleDate(c1.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                SimpleDate v2 = new SimpleDate(c2.getDate().getCIDate().getDateInCIDate().getDateGco().getText());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByDateUpdatedAscending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

<<<<<<< HEAD
    void sortOMEntriesByDateUpdatedDescending(List<EntryOM> list) {
        Comparator comparator = new Comparator<EntryOM>() {
            public int compare(EntryOM c1, EntryOM c2) {
=======
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
    void sortISOEntriesByDateUpdatedDescending(List<EntryISO> list)
    {
        Comparator comparator = new Comparator<EntryISO>()
        {
            public int compare(EntryISO c1, EntryISO c2)
            {
<<<<<<< HEAD
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntries(List<EntryISO> entries) {
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

    void sortISOEntriesByTitleAscending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str1.compareTo(str2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByTitleDescending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                String str1 = c1.getTitle().trim();
                String str2 = c2.getTitle().trim();
                return str2.compareTo(str1);
            }
        };
        sortCollection(list, comparator);
    }

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

    void sortISOEntriesByDateUpdatedAscending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v1.compareTo(v2);
            }
        };
        sortCollection(list, comparator);
    }

    void sortISOEntriesByDateUpdatedDescending(List<EntryISO> list) {
        Comparator comparator = new Comparator<EntryISO>() {
            public int compare(EntryISO c1, EntryISO c2) {
=======
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4
                SimpleDate v1 = new SimpleDate(c1.getUpdated());
                SimpleDate v2 = new SimpleDate(c2.getUpdated());
                return v2.compareTo(v1);
            }
        };
        sortCollection(list, comparator);
    }
}
