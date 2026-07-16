$ErrorActionPreference = "Stop"

python -m pip install --upgrade pip
python -m pip install -r requirements.txt

Write-Host "Setup complete. Run tests with: .\run-tests.ps1" -ForegroundColor Green
