export interface Item {
  title: string;
  id: number;
  node_id: string;
  name: string;
  full_name: string;
  items: Items;
}

export declare type Items = Item[];

/*
export interface SearchGameResponse {
  title: string;
  id: number;
  items: Items;
}
*/
