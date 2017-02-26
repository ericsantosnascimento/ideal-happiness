package br.com.acme.controller;

import br.com.acme.AcmeApplicationTest;
import br.com.acme.exception.AcmeServiceException;
import br.com.acme.request.InvoiceRequest;
import br.com.acme.util.MockCreatorUtil;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

/**
 * Created by eric-nasc on 26/02/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AcmeApplicationTest.class)
@ActiveProfiles("test")
@Transactional
public class InvoiceControllerTest {

    @Autowired
    private InvoiceController invoiceController;

    @Test
    public void listByCustomerIdWithSuccess() throws Exception {
        given().standaloneSetup(invoiceController)
                .contentType(ContentType.JSON)
                .when()
                .get("/v1.0/invoices?customerId=1")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void listByMonthWithSuccess() throws Exception {
        given().standaloneSetup(invoiceController)
                .contentType(ContentType.JSON)
                .when()
                .get("/v1.0/invoices?customerId=1&month=11")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void listByAddressIdWithSuccess() throws Exception {
        given().standaloneSetup(invoiceController)
                .contentType(ContentType.JSON)
                .when()
                .get("/v1.0/invoices?customerId=1&addressId=f4262d94")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void listByfilterWithSuccess() throws Exception {
        given().standaloneSetup(invoiceController)
                .contentType(ContentType.JSON)
                .when()
                .get("/v1.0/invoices?customerId=1&filter=shop")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(expected = NestedServletException.class)
    public void listByInvalidMonthWithoutSuccess() throws Exception {
        given().standaloneSetup(invoiceController)
                .contentType(ContentType.JSON)
                .when()
                .get("/v1.0/invoices?customerId=1&month=13")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void save() throws Exception {

        InvoiceRequest mockInvoiceRequest = MockCreatorUtil.createMockInvoiceRequest();
        given().standaloneSetup(invoiceController)
                .contentType(ContentType.JSON)
                .body(mockInvoiceRequest)
                .when()
                .post("/v1.0/invoices")
                .then()
                .assertThat()
                .statusCode(201);
    }

}