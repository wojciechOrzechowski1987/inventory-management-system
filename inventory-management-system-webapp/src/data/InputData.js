export const menuOption = {
  status: [
    {
      name: "Status - Region",
      link: "/district",
      activeIndex: 0,
      selectedIndex: 0,
    },
    {
      name: "Status - Projekt",
      link: "/project",
      activeIndex: 0,
      selectedIndex: 1,
    },
  ],
  demand: [
    {
      name: "Zapotrzebowania",
      link: "/demand",
      activeIndex: 1,
      selectedIndex: 10,
    },
  ],
  order: [
    {
      name: "Zamówienia",
      link: "/order",
      activeIndex: 2,
      selectedIndex: 20,
    },
  ],

  administration: [
    {
      name: "Grupy Materiałowe",
      link: "/administration/materialGroups",
      activeIndex: 3,
      selectedIndex: 30,
    },
    {
      name: "Podgrupy Materiałowe",
      link: "/administration/materialSubgroups",
      activeIndex: 3,
      selectedIndex: 31,
    },
    {
      name: "Materiały",
      link: "/administration/material",
      activeIndex: 3,
      selectedIndex: 32,
    },
    {
      name: "Dostawca",
      link: "/administration/vendor",
      activeIndex: 3,
      selectedIndex: 33,
    },
    {
      name: "Produkty",
      link: "/administration/productItem",
      activeIndex: 3,
      selectedIndex: 34,
    },
  ],
};

export const routes = [
  { name: "Status", link: "/status", activeIndex: 0, administration: false },
  {
    name: "Zapotrzebowania",
    link: "/demand",
    activeIndex: 1,
    administration: false,
  },
  { name: "Zamówienia", link: "/order", activeIndex: 2, administration: false },
  {
    name: "Zarządzanie",
    link: "/administration",
    activeIndex: 3,
    administration: true,
  },
];
