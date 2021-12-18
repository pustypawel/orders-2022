package pl.edu.wszib.order.application.product;

public class ProductModuleInitialization {
    private final ProductFacade productFacade;

    public ProductModuleInitialization() {
        final InMemoryProductRepository productRepository = new InMemoryProductRepository();
        this.productFacade = new ProductModule(productRepository).getFacade();
        this.init();
    }

    private void init() {
        productFacade.create(ProductSamples.CHOCOLATE);
        productFacade.create(ProductSamples.COCA_COLA_ZERO);
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }
}
