<<<<<<< HEAD
1.SRP (Responsabilidad Única): La clase gestiona datos, calcula descuentos, guarda archivos y envía correos.


2.OCP (Abierto/Cerrado): Para añadir un nuevo tipo de descuento (ej. VIP), hay que modificar el código interno con más if.


3.DIP (Inversión de Dependencias): Depende directamente de la implementación de FileWriter para guardar datos.
+1


4.ISP (Segregación de Interfaz): No existen interfaces, lo que obliga a depender de una clase concreta pesada.
=======
classDiagram
    OrderSubject <|-- OrderService
    OrderObserver <|.. EmailNotifier
    OrderObserver <|.. SMSNotifier
    OrderObserver <|.. LogNotifier
    Product <|-- Electronics
    Product <|-- Clothing
    Product <|-- Food
    PricingStrategy <|.. BlackFridayPricing
    PricingStrategy <|.. MemberPricing
    PricingStrategy <|.. RegularPricing
    ProductFactory ..> Product

    Proyecto desarrollado para la materia de Patrones de diseño - 2026.
>>>>>>> c5712f0 (Refactorizacion: Aplicacion de principios SOLID y desacoplamiento de clases)
