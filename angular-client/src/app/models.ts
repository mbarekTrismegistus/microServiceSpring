export interface Customer {
  id: number;
  name: string;
  email: string;
}

export interface Product {
  id: number;
  name: string;
  price: number;
  quantity: number;
}

export interface BillItem {
  id: number;
  productId: number;
  productName: string;
  unitPrice: number;
  quantity: number;
}

export interface Bill {
  id: number;
  billingDate: string;
  customer: Customer;
  items: BillItem[];
}

export interface CreateBill {
  customerId: number;
  items: Array<{ productId: number; quantity: number }>;
}
