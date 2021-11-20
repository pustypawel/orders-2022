package pl.edu.wszib.order.application.product;

public class ProductRepositoryInitialization {

    public void init(final ProductFacade productFacade) {
        productFacade.create(ProductSamples.CHOCOLATE);
    }
}
