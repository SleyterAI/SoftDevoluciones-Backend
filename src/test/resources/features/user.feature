Feature: User Authentication

  Background:
    * url baseUrl
    * path 'api/auth/login'
    * header Content-Type = 'application/json'


# ============================================================
# LOGIN - CASOS EXITOSOS
# ============================================================

  Scenario: Login exitoso con credenciales válidas

    Given request
    """
    {
        "email": "admin@tienda.com",
        "password": "12345678"
    }
    """

    When method POST
    Then status 200

    And match response.token == '#string'

# ============================================================
# LOGIN - CASOS FALLIDOS
# ============================================================

  Scenario: Login con password incorrecta

    Given request
    """
    {
        "email": "admin@tienda.com",
        "password": "passwordIncorrecta"
    }
    """

    When method POST
    Then status 401


  Scenario: Login con usuario inexistente

    Given request
    """
    {
        "email": "usuario_inexistente@test.com",
        "password": "123456"
    }
    """

    When method POST
    Then status 400


  Scenario: Login con email vacío

    Given request
    """
    {
        "email": "",
        "password": "12345678"
    }
    """

    When method POST
    Then status 400


  Scenario: Login con password vacío

    Given request
    """
    {
        "email": "admin@tienda.com",
        "password": ""
    }
    """

    When method POST
    Then status 400


  Scenario: Login sin email

    Given request
    """
    {
        "password": "12345678"
    }
    """

    When method POST
    Then status 400


  Scenario: Login sin password

    Given request
    """
    {
        "email": "admin@tienda.com"
    }
    """

    When method POST
    Then status 400


  Scenario: Login con body vacío

    Given request
    """
    {}
    """

    When method POST
    Then status 400