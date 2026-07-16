param(
    [ValidateSet("chrome", "firefox", "edge", "safari")]
    [string]$Browser,
    [ValidateSet("desktop", "tablet", "mobile")]
    [string]$Device = "desktop",
    [switch]$Headless
)

$ErrorActionPreference = "Stop"

$PytestArgs = @("-m", "pytest", "--device", $Device)
if ($Browser) { $PytestArgs += @("--browser", $Browser) }
if ($Headless) { $PytestArgs += "--headless" }
$PytestArgs += $args

python @PytestArgs
exit $LASTEXITCODE
